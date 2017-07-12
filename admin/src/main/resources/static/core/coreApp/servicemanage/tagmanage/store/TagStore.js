Ext.define("core.servicemanage.tagmanage.store.TagStore", {
    extend: 'Ext.data.Store',
    model: 'core.servicemanage.tagmanage.model.TagModel',
    pageSize: 500,
    proxy: {
        type: "ajax",
        url: "flowTag/listTags",
        actionMethods: {
            create: 'POST',
            read: 'POST', // by default GET
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
    autoLoad: true
});