Ext.define("core.servicemanage.bpelmanage.store.UserStore", {
    extend: 'Ext.data.Store',
    model: 'core.basicinfomanage.peoplemanage.model.PeopleModel',
    pageSize: 50,
    proxy: {
        type: "ajax",
        url: "flow/listUser",
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