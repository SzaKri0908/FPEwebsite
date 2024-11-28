<template>
  <div class="adminPageNewsContainer">
    <div class="adminPageNews" id="admin-page-news">
      <FPEDataTable
        ref="fpeDataTable"
        :columns="getHeaderFields"
        :actions="getActions"
        :rowActions="getRowActions"
        :additionalConfig="getAdditionalTableConfig"
        @customClick="buttonWasClicked"
        :apiCallback="apiCallback"/>
    </div>
    <!-- Új lista elem felvétele modal -->
    <b-modal ref="modalForm" @hide="resetModal" size="lg">
      <b-col cols="12">
      <div class="formulateForm">
        <FormulateForm
          v-model="values"
          :schema="formSchema"
          @submit="saveNewsData"/>
      </div>
      </b-col>
      <template slot="modal-footer">
        <b-button size="l" variant="danger" @click="hideModal()" class="modal-action-button">
            <i class="far fa-times-circle"></i> Nem
        </b-button>
        <b-button size="l" variant="success" @click="saveNewsData()" class="modal-action-button">
            <i class="fas fa-check"></i> Mentés
        </b-button>
      </template>
    </b-modal>
    <!-- Szerkesztés modal -->
    <b-modal ref="modalEditForm" @hide="resetEditModal" size="lg">
      <div class="formulateForm">
        <FormulateForm
          v-model="editValues"
          :schema="formEditSchema"
          @submit="saveEditNewsData"/>
      </div>
      <div class="imageUploader mt-4">
        <FormulateInput
          v-model="imageValues"
          type="image"
          name="headshot"
          label="Töltsön fel egy képet"
          help="Válasszon ki .png, .jpg formátumot a feltöltéshez"
          validation="mime:image/jpeg,image/png,image/gif"
        />
      </div>
      <template slot="modal-footer">
        <b-button size="l" variant="danger" @click="hideEditModal()" class="modal-action-button">
            <i class="far fa-times-circle"></i> Nem
        </b-button>
        <b-button size="l" variant="success" @click="saveEditNewsData()" class="modal-action-button">
            <i class="fas fa-check"></i> Mentés
        </b-button>
      </template>
    </b-modal>

    <CustomDeleteDialog :modal-title="'Valóban törölni szeretné?'"
                        ref="customDialogRef"
                        @custom-dialog-ok="deleteRow"/>
  </div>
</template>

<script>
import Vue from 'vue';
import { mapGetters } from 'vuex'
import FPEDataTable from '../components/FPEDataTable.vue'
import Api from '../util/Api'
import Loading from 'vue-loading-overlay';
import 'vue-loading-overlay/dist/vue-loading.css';
import CustomDeleteDialog from '../components/CustomDeleteDialog.vue';

Vue.use(Loading);

export default {
    name: 'AdminPageNews',
    components: {
      FPEDataTable,
      CustomDeleteDialog
    },
    data () {
      return {
        values: {},
        editValues: {},
        imageValues: {},
        fileValues: null,
        fullPage: true,
      }
    },
    watch: {
      'values.newsArray': {
        deep: true,
        handler(newVal) {
          debugger
          if (this.internalFlag) return;
          this.internalFlag = true;
          
          const mainNewsItems = newVal.filter(item => item.isMainNews);
          if (mainNewsItems.length > 1) {
            const latestMainNews = mainNewsItems[mainNewsItems.length - 1];
            newVal.forEach(item => {
              console.log("🦥 ~ handler ~ item:", item.__id);
              console.log("🦥 ~ handler ~ latestMainNews:", latestMainNews.__id);
              if (item.__id !== latestMainNews.__id) {
                item.isMainNews = false;
              }
            });
          }
            this.internalFlag = false;
        }
      }
    },
    methods: {
      apiCallback(params) {
        params.params.filter = {
          ...params.params.filter
        }
        return Api.getNews(params);
      },
      refreshGrid() {
        this.$refs.fpeDataTable.fetchData();
      },
      showSuccessToast(apiMessage) {
        //const message = apiMessage
        this.$toast.success(apiMessage, {
          position: 'top-center',
          timeout: 3000
        })
      },
      showErrorToast(apiMessage){
        //const message = `Sikertelen mentés! \n${apiMessage}`
        this.$toast.error(apiMessage, {
          position: 'top-center',
          timeout: 3000
        })
      },
      onCancel() {
        console.log('User cancelled the loader.');
      },
      saveEditNewsData(){

        let loader = this.$loading.show({
            container: this.fullPage ? null : this.$refs.modalEditForm,
            canCancel: false,
        });
        
        if(this.imageValues.files){          
          const imagesExtracted = this.imageValues.files;
          var imageFileBase64 = imagesExtracted[0].previewData;
        }

        const cleanValues = {
          id: this.editValues.id,
          title: this.editValues.title,
          facebookLink: this.editValues.facebookLink,
          images: imageFileBase64 ? imageFileBase64 : null
        };

        const minimumLoadingTime = 1500;
        const startTime = Date.now();

        Api.saveNews(cleanValues)
          .then(response => {
              console.log(response);
              this.showSuccessToast(response.data.message);
              this.$refs.modalEditForm.hide();
              const elapsedTime = Date.now() - startTime;
              if (elapsedTime < minimumLoadingTime) {
                  setTimeout(() => {
                      loader.hide();
                  }, minimumLoadingTime - elapsedTime);
              } else {
                  this.$refs.modalEditForm.hide();
                  loader.hide(); 
              }
          })
          .catch(error => {
              console.error('Error saving news:', error);
              this.showErrorToast(error.response.data.message)
              this.$refs.modalEditForm.hide();
              const elapsedTime = Date.now() - startTime;
              if (elapsedTime < minimumLoadingTime) {
                  setTimeout(() => {
                      loader.hide(); 
                  }, minimumLoadingTime - elapsedTime);
              } else {
                  loader.hide(); 
              }
          });
      },
      saveNewsData() {
        let loader = this.$loading.show({
            container: this.fullPage ? null : this.$refs.modalForm,
            canCancel: false,
        });
        
        const entriesWithImages = this.values.newsArray.map(entry => ({
            title: entry.title,
            facebookLink: entry.facebookLink,
            isMainNews: entry.isMainNews,
            image: entry.image.files.length > 0 ? {
                file: entry.image.files[0].previewData,
                name: entry.image.files[0].name,
                type: entry.image.files[0].file.type
            } : null
        }));

        const minimumLoadingTime = 1500;
        const startTime = Date.now();

        Api.saveNews(entriesWithImages)
            .then(response => {
                console.log(response);
                this.showSuccessToast(response.data.message);
                this.$refs.modalForm.hide();
                const elapsedTime = Date.now() - startTime;
                if (elapsedTime < minimumLoadingTime) {
                    setTimeout(() => {
                        loader.hide();
                    }, minimumLoadingTime - elapsedTime);
                } else {
                    loader.hide(); 
                }
            })
            .catch(error => {
                console.error('Error saving news:', error);
                this.showErrorToast(error.response.data.message)
                //this.$refs.modalForm.hide();
                //this.$refs['modalForm'].hide()
                const elapsedTime = Date.now() - startTime;
                if (elapsedTime < minimumLoadingTime) {
                    setTimeout(() => {
                        loader.hide(); 
                    }, minimumLoadingTime - elapsedTime);
                } else {
                    loader.hide(); 
                }
            });
      },
      deleteConfirm(data) {
        this.$refs.customDialogRef.showDialog(data);
      },
      deleteRow(data) {
          let self = this;
          let loader = this.$loading.show({
              container: this.fullPage ? null : this.$refs.customDialogRef,
              canCancel: false,
          });
          Api.deleteNews(data['values.id']) 
              .then((response) => {
                  self.refreshGrid();
                  loader.hide();
                  this.showSuccessToast(response.data.message);
              })
              .catch(errMsg => {
                  loader.hide();
                  this.showErrorToast(errMsg.response.data.message);
                  console.error(errMsg);
              });
      },
      buttonWasClicked(event, data) {
          let dataDetails;
          switch (event.event_payload) {
              case "newRow":
                  this.$refs.modalForm.show()
                  break;
              case "modifyRow":
                  this.editValues = data
                  this.$refs.modalEditForm.show()
                  break;  
              case "deleteRow":
                  dataDetails = {
                      'values.id': data.id,
                      'values.title': data.title,
                      'values.link': data.facebookLink
                  };
                  this.deleteConfirm(dataDetails);
                  break;
              case "listDeletedRows":
                this.withDeleted = !this.withDeleted;
                break;
              case "unDeleteRow":
                this.unDeleteRow(data);
                break;
          }
      },
      resetModal(){
        this.values = {}
        this.imageValues = {}
      },
      resetEditModal(){
        this.editValues = {}
      },
      hideModal(){
        this.values = {}
        this.imageValues = {}
        this.$refs.modalForm.hide()
      },
      hideEditModal(){
        this.editValues = {}
        this.$refs.modalEditForm.hide()
      },
    },
    computed: {
      ...mapGetters('adminPageNews', ['getHeaderFields', 'getRowActions', 'getAdditionalTableConfig', 'getActions']),
        formSchema() {
          return [
            {
              "component": "h3",
              children: 'Új hír rögzítése'
            },
            {
              type: "group",
              name: "newsArray",
              repeatable: true,
              addLabel: "+ Hír",
              initialValue: [{}],
              children: [
                {
                  component: "FormulateInput",
                  type: "text",
                  name: "title",
                  validation: "required",
                  label: "Cím"
                },
                {
                  component: "FormulateInput",
                  type: "url",
                  name: "facebookLink",
                  validation: "required",
                  label: "Facebook link",
                  placeholder: "https://"
                },
                {
                  component: "FormulateInput",
                  type: "image",
                  name: "image",
                  label: "Kép feltöltése",
                  help: "Válasszon ki .png, .jpg formátumot a feltöltéshez",
                  validation: "mime:image/jpeg,image/png",
                  imageBehavior: "preview",
                  //multiple: true
                },
                {
                  class: "flex-wrapper",
                  component: "FormulateInput",
                  type: "checkbox",
                  name: "isMainNews",
                  label: "Főhír",
                }
              ]
            }
        ];
    },
    formEditSchema() {
      return [
        {
          component: "h3",
          children: 'Szerkesztés'
        },
        {
          component: "FormulateInput",
          type: "text",
          name: "title",
          validation: "required",
          label: "Cím"
        },
        {
          component: "FormulateInput",
          type: "url",
          name: "facebookLink",
          validation: "required",
          label: "Facebook link",
          placeholder: "https://"
        },
      ];
    }
  }
}
</script>

<style>
.modal-action-button {
  box-shadow: 2px 2px 5px rgba(0, 0, 0, 0.2);
}
.formulate-input .formulate-input-element {
    max-width: 46em !important;
    margin-bottom: 0.1em !important;
}
.formulate-input[data-classification=box] .formulate-input-label--after {
    margin-bottom: 0.5rem !important;
}
</style>