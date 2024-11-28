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
                btn_text: '<i class="fas fa-plus"></i> Új esemény feltöltése',
                event_name: "customClick",
                class: "btn btn-info my-custom-class",
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
            // {
            //     name: "title",
            //     label: "Cím",
            //     filter: {
            //         type: "simple",
            //     },
            //     visibility: true,
            // },
            {
                name: "eventDate",
                label: "Esemény dátuma",
                filter: {
                    type: "simple",
                },
                slot_name: "date_format",
                visibility: true,
            },
            {
                name: "facebookEventLink",
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
    }
}
