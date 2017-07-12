Ext.define("core.servicemanage.bpelmanage.store.AllUserStore", {
    extend: 'Ext.data.Store',
    model: 'core.basicinfomanage.peoplemanage.model.PeopleModel',
    pageSize: 50,
    proxy: {
        type: "ajax",
        url: "flow/listUser",
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
    autoLoad: false
});