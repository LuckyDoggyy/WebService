Ext.define("core.basicinfomanage.peoplemanage.store.PeopleStore", {
    extend: 'Ext.data.Store',
    model: 'core.basicinfomanage.peoplemanage.model.PeopleModel',
    pageSize: 50,
    proxy: {
        type: "ajax",
        url: "user/list",
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