Ext.define("core.servicemanage.bpelmanage.store.BPStore", {
    extend: 'Ext.data.Store',
    model: 'core.servicemanage.bpelmanage.model.BPModel',
    pageSize: 500,
    proxy: {
        type: "ajax",
        url: "flow/listFlows",
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