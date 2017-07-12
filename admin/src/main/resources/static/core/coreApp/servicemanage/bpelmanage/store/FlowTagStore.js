Ext.define("core.servicemanage.bpelmanage.store.FlowTagStore", {
    extend: 'Ext.data.Store',
    model: 'core.servicemanage.bpelmanage.model.FlowTagModel',
    pageSize: 50,
    proxy: {
        type: "ajax",
        url: "flowTag/listFlowTags",
        actionMethods: {
            create: 'POST',
            read: 'POST',
            update: 'POST',
            destroy: 'POST'
        },
        reader: {
            type: "json",
            root: "rows",
            totalProperty: 'totalCount'
        },
        writer: {
            type: "json"
        }
    },
    autoLoad: false
});