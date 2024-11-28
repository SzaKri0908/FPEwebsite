<template>
    <div id="FPEDatatable">
        <div class="table-container justify-content-center">
            <vue-bootstrap4-table
            class="fpe-data-table" 
            :rows="rows" 
            :columns="columns"
            :classes="classes" 
            :config="config"
            :actions="actions"
            v-on="$listeners"
            :totalRows="total_rows"
            @on-change-query="onChangeQuery"
            @refresh-data="fetchData">
             <template slot="empty-results">
                Nincs találat 
             </template>
            <template slot="refresh-button-text">
                <i class="fas fa-sync-alt"></i> Frissítés
            </template>
            <template slot="pagination-info" slot-scope="props">
                A találatok száma: {{props.filteredRowsLength}}
            </template>
            <template slot="buttons" slot-scope="props">
                <button v-for="(rowAction, index) in rowActions"
                        :key="index"
                        :class="rowAction.class"
                        :title="rowAction.title"
                        :value="props.row.id" class="table-action-button"
                        type="button"
                        @click="$emit('customClick', {event_payload: rowAction.event_payload}, props.row)"
                        v-html="rowAction.btn_text">
                </button>
                        <!-- :disabled="setRowActionButtonDisable(props.row, rowAction)" -->
                        <!-- v-if="setRowActionButtonVisible(props.row, rowAction, additionalData)" -->
            </template>
            <template slot="date_format" slot-scope="props">
                {{formatDate(props.cell_value)}}
            </template>
            </vue-bootstrap4-table>
        </div>
    </div>
</template>
 
<script>
import VueBootstrap4Table from 'vue-bootstrap4-table'

export default {
    name: 'FPEDatatable',
    components: {
        VueBootstrap4Table
    },
    props: {
        columns: {type: Array},
        actions: {type: Array},
        rowActions: {type: Array},
        apiCallback: {type: Function},
        additionalConfig: {type: Object},
        additionalData: {type: Object},
        inputData: {type: Array},
        orderBy: {type: Array, default: () => [false, 'name']}, //abc sorrendbe rakja a táblázatot
    },
    data() {
        return {
            fullPage: true,
            rows: [],
            classes: {
                table: {
                    "table-striped table-bordered": true
                }
            },
            // config: {
            //     checkbox_rows: false,
            //     rows_selectable: true,
            //     pagination_info: true,
            //     show_refresh_button: false, // default is also true
            //     global_search: {
            //         placeholder: "Keresés...",
            //     },
            //     show_reset_button: false,
            //     pagination: true,
            //     num_of_visibile_pagination_buttons: 7,
            //     per_page: 5,
            //     initOnRender: true,
            //     per_page_options: [5, 10, 20, 30],
            //     server_mode: false,
            //     ...this.additionalConfig
            // },
            config: {
                preservePageOnDataChange: true,
                pagination: true,
                num_of_visible_page: 7,
                server_mode: false,
                per_page: 10,
                per_page_options: [5, 10, 20, 30],
                selected_rows_info: true,
                checkbox_rows: false,
                show_refresh_button: true,
                show_reset_button: false,
                initOnRender: true,
                global_search: {
                    placeholder: "Keresés...",
                    visibility: false,
                    case_sensitive: false,
                    showClearButton: false,
                    searchOnPressEnter: false,
                    searchDebounceRate: 1000
                },
                ...this.additionalConfig
            },
            queryParams: {
                sort: [],
                filters: [],
                global_search: "",
                per_page: 20,
                page: 0,
            },
            total_rows: 0
        }
    },
    mounted() {
        if (this.config.initOnRender && !this.config.server_mode) {
            this.fetchData();
        }
    },
    methods: {
        // setRowActionButtonDisable(row, rowAction) {
        //     if (rowAction.additionalButtonDisable) {
        //         switch(rowAction.additionalButtonDisable) {
        //             case "canEdit":
        //                 return !row.canEdit;
        //         }
        //     } else {
        //         return false;
        //     }
        // },
        onChangeQuery(queryParams) {
            this.queryParams = queryParams;
            this.fetchData();
        },
        fetchData() {
                let self = this;
                
                if (this.config.checkbox_rows) {
                    // this.$refs['vue-bootstrap4-table'].unSelectAllItems();
                }
                if (this.apiCallback) {
                    //this.$loading(true);
                    let loader = this.$loading.show({
                        canCancel: false,
                    });
                    
                    let filter = {};
                    this.queryParams.filters.forEach((item) => {
                        filter[item.name] = item.text;
                    });
                    
                    let sort = {};
                    this.queryParams.sort.forEach((item) => {
                        sort.column = item.name;
                        sort.orderAsc = item.order === 'asc';
                    });
                    
                    setTimeout(() => {
                        self.apiCallback(self.config.server_mode === true ? {
                            params: {
                                "pageIndex": self.queryParams.page - 1,
                                "pageSize": self.queryParams.per_page,
                                "filter": filter,
                                "sort": sort,
                                "pageCount": self.queryParams.page
                            }
                        } : {}).then(function (response) {
                            //self.$loading(false);
                            setTimeout(() => {
                                loader.hide();
                            }, 1000);
                            if (response.data.rows) {
                                self.rows = response.data.rows;
                                self.total_rows = response.data.total;
                            } else {
                                self.rows = response.data;
                            }
                            if(self.orderBy[0]){ //abc sorrendbe rakja a táblázatot
                                self.rows = self.rows.sort((a, b) => {
                                    const aValue = a[self.orderBy[1]] ? a[self.orderBy[1]].trim() : '';
                                    const bValue = b[self.orderBy[1]] ? b[self.orderBy[1]].trim() : '';

                                    if (aValue === '' && bValue === '') { //ez üres adatot a végére rakja
                                        return 0;
                                    } else if (aValue === '') {
                                        return 1; 
                                    } else if (bValue === '') {
                                        return -1; 
                                    }

                                    return aValue.localeCompare(bValue);
                                });
                            }
                            self.$emit('getRows', self.rows)
                        }).catch(function (errMsg) {
                            //self.$loading(false);
                            setTimeout(() => {
                                loader.hide();
                            }, 1000);
                            console.error(errMsg);
                            self.rows = [];
                            if (errMsg.response && errMsg.response.data && errMsg.response.data.reason) {
                                if (errMsg.response.data.type) {
                                    switch (errMsg.response.data.type) {
                                        case "DOR":
                                            self.$refs.eesztDorWarningModal.showDialog();
                                            break;
                                        case "WARN":
                                            self.$toast.warn({
                                                title: 'Figyelem',
                                                message: errMsg.response.data.reason
                                            });
                                            break;
                                        default:
                                            self.$toast.error({
                                                title: 'Sikertelen lekérdezés',
                                                message: errMsg.response.data.reason
                                            });
                                            break;
                                    }
                                } else {
                                    self.$toast.error({
                                        title: 'Sikertelen lekérdezés',
                                        message: errMsg.response.data.reason
                                    });
                                }
                            }
                        });
                    })
                } else {
                    this.rows = this.inputData ? this.inputData : [];
                    this.$emit('getRows', this.rows)
                }
            },
            formatDate(date) {
                if (date) {
                    return this.$moment(date).format("YYYY.MM.DD.");
                } else {
                    return "";
                }
            },
    }
}
</script>

<style type="scss" scoped>
.table-container{
  padding: 50px;
  margin: 0 auto;
  max-width: 100%; 
  width: 92%; 
  overflow-y: hidden;
}
.fpe-data-table{
   box-shadow: 0px 5px 40px rgba(29, 39, 92, 0.4) !important;
}
.fpe-data-table .table-active, .table-active > th, .table-active > td {
    padding-bottom: 0 !important;
}
input[data-v-75effae8]::placeholder {
    color: transparent !important;
}
#FPEDatatable >>> .table-action-button {
    padding: 2px 7px;
    margin-left: 2px;
    margin-right: 2px;
    box-shadow: 2px 2px 5px rgba(0, 0, 0, 0.2);
}
#FPEDatatable >>> .table-action-button i{
    font-size: 13px;
}
#FPEDatatable >>> button{
    box-shadow: 2px 2px 5px rgba(0, 0, 0, 0.2);
}
/* input[data-v-75effae8] {
    margin-right: 100px;
    overflow-x: hidden;
} */
</style>
