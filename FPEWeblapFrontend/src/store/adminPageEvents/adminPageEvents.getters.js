export default {
    isFormOpen: state => state.formIsOpen,
    isGridOpen: state => state.gridIsOpen,

    getAdditionalTableConfig: state => state.additionalTableConfig,
    getActions: state => state.actions,
    getFormActions: state => state.formActions,
    getRowActions: state => state.rowActions,
    getHeaderFields: state => state.headerFields,
    getSearchFields: state => state.searchFields,
    getFormFields: state => state.formFields
}
