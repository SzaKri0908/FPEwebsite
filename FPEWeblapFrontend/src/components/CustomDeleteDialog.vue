<template>
    <b-modal ref="custom-confirm-dialog" :title="modalTitle" @ok="handleOk">
        <div class="d-block">
            <div class="mb-3">
                {{ description }}
            </div>
            <ul v-if="listDetails">
                <li v-for="(value, name) in rowData" v-bind:key="name">
                    {{ $t(name) }}: {{ value }}
                </li>
            </ul>
        </div>

        <div v-if="needReason" class="d-block" :style="cssVars">
            <b-form-group :class="{filled: reasonText }" class="smooth-form-group">
                    <textarea v-model="reasonText" style="width: 100%" rows="3">
                    </textarea>
                  <label class="smooth-label required">Indoklás</label>
            </b-form-group>
        </div>

        <template slot="modal-footer" slot-scope="{ ok, cancel}">
            <b-button size="sm" variant="secondary" @click="cancel()" class="modal-action-button">
                Mégse
            </b-button>
            <b-button v-if="forDelete" size="sm" variant="danger" @click="ok()" class="modal-action-button">
                Törlés
            </b-button>
            <b-button v-else size="sm" variant="success" @click="ok()" class="modal-action-button">
                Igen
            </b-button>
        </template>
    </b-modal>
</template>

<script>

    export default {
        name: "CustomConfirmDialog",
        props: {
            modalTitle: {type: String},
            needReason: {type: Boolean, default: false},
            needPatientNoticeCheckBox: {type: Boolean, default: false},
            forDelete: {type: Boolean, default: true},
            listDetails: {type: Boolean, default: true},
        },
        data() {
            return {
                rowData: {},
                reasonText: '',
                patientNotice: true,
                description: '',
                extendedDetails: ''
            }
        },
        methods: {
            showDialog(data, description,extendedDetails){
                this.description = description;
                this.rowData = data;
                this.extendedDetails = extendedDetails;
                this.reasonText = '';
                this.$refs['custom-confirm-dialog'].show()
            },
            handleOk(){
                if (this.needReason && this.reasonText !== '') {
                    this.$emit('custom-dialog-ok', this.rowData, this.reasonText, this.patientNotice);
                } else if (this.needReason && this.reasonText === '') {
                    this.$toast.warn({
                        title: 'Figyelem',
                        message: 'Indoklás kitöltése kötelező!'
                    });
                } else if (!this.needReason) {
                    this.$emit('custom-dialog-ok', this.rowData);
                }
            }
        },
    }
</script>

<style scoped>
.modal-action-button {
  box-shadow: 2px 2px 5px rgba(0, 0, 0, 0.2);
}

</style>
