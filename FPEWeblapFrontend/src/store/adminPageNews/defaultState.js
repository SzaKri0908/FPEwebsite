import Api from '@/util/Api'

export default function getInitState() {
    return {
        formIsOpen: false,
        gridIsOpen: true,
        additionalTableConfig: {
            server_mode: true,
            show_reset_button: false,
            global_search: {
                visibility: false,
            }
        },
        actions: [
            {
                btn_text: '<i class="fas fa-plus"></i> Új hír feltöltése',
                event_name: "customClick",
                class: "btn btn-info my-custom-class",
                event_payload: 'newRow'
            },
            {
                btn_text: '<i class="fas fa-search"></i> Keresés',
                event_name: "customClick",
                class: "btn btn-secondary my-custom-class",
                event_payload: 'newRow'
            }
        ],
        rowActions: [
            {
                btn_text: '<i class="fas fa-edit"></i>',
                title: 'Szerkesztés',
                event_name: "customClick",
                class: "btn btn-outline-info",
                event_payload: 'modifyRow',
            },
            {
                btn_text: '<i class="fas fa-trash"></i>',
                title: 'Törlés',
                event_name: "customClick",
                class: "btn btn-outline-danger",
                event_payload: 'deleteRow',
            },
            // {
            //     btn_text: '<i class="fas fa-undo"></i>',
            //     title: 'Visszaállítás',
            //     event_name: "customClick",
            //     class: "btn btn-outline-warning",
            //     seeCaseOfDeleted: true,
            //     event_payload: 'unDeleteRow'
            // }
        ],
        headerFields: [
            {
                name: "id",
                label: "ID",
                filter: {
                    type: "simple",
                },
                visibility: false,
            },
            {
                name: "title",
                label: "Cím",
                filter: {
                    type: "simple",
                },
                visibility: true,
            },
            {
                name: "facebookLink",
                label: "Facebook elérhetőség",
                visibility: true,
            },
            {
                name: "imageUrl",
                label: "Kép url",
                visibility: false,
            },
            {
                name: "text",
                label: "Szöveg",
                visibility: true,
            },
            {
                name: "buttons",
                label: "Opciók",
                visibility: true,
            }
        ],
        searchFields: [
            {
                name: "referralId",
                label: "Beutaló sorszáma",
                formVisible: false
            },
            {
                name: "name",
                label: "Beutaló orvos",
                formVisible: true,
                formSize: 4,
                formType: 'select',
                selectOptions: {
                    apiCallback: Api.getAllDoctor,
                    formatText: function (val) {
                        return val.text;
                    },
                    formatOption: function (val) {
                        return val.text;
                    },
                    minimumInputLength: 0,
                    searchOnServer: false,
                    filteredAttr: 'text',
                    returnValue: 'id',
                    locale: {
                        searchPlaceholder: 'Keresés...',
                        noResults: 'Nincs találat'
                    }
                }
            },
            {
                name: "vaccine",
                label: "Beutaló orvos szakmája",
                formType: "text",
                formSize: 4,
                formVisible: true
            },
            {
                name: "occupationalIndication",
                label: "Kért vizsgálat",
                formType: "text",
                formSize: 4,
                formVisible: true
            },
            {
                name: "dosage",
                label: "Megjegyzés",
                formType: "text",
                formSize: 4,
                formVisible: true,
            },
            {
                name: "basicImmunization",
                label: "Beutalás ideje",
                formType: "date",
                formSize: 4,
                formVisible: true
            },
            {
                name: "boosterVaccination",
                label: "Felhasználva",
                formType: 'select', 
                formSize: 4,
                formVisible: true,
                selectOptions: {
                    externalOptionList: [
                        {
                            id: "1",
                            text: 'Felhasználás dátuma',
                        },
                        {
                            id: "2",
                            text: 'Nem került felhasználásra',
                        },
                        {
                            id: "3",
                            text: 'Lejárt',
                        }
                    ],
                    formatText: function (val) {
                        return val.text;
                    },
                    formatOption: function (val) {
                        return val.text;
                    },
                    minimumInputLength: 0,
                    searchOnServer: false,
                    filteredAttr: 'text',
                    returnValue: 'id',
                    locale: {
                        searchPlaceholder: 'Keresés...',
                        noResults: 'Nincs találat'
                    }
                }
            },
        ]
    }
}
