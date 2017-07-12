Ext.define("core.servicemanage.tagmanage.store.TagOptStore", {
    extend: 'Ext.data.Store',
    model: 'core.servicemanage.tagmanage.model.TagOpt',
    pageSize: 500,
    proxy: {
        type: "ajax",
        url: "flowTag/getTagOption",
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
    autoLoad: true
});