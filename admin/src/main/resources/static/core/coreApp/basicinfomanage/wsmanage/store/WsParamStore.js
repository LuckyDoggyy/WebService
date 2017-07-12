Ext.define("core.basicinfomanage.wsmanage.store.WsParamStore", {
    extend: 'Ext.data.Store',
    model: 'core.basicinfomanage.wsmanage.model.WsParamModel',
    pageSize: 50,
    proxy: {
        type: "ajax",
        url: "ws/listWsParam",
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