Ext.define("core.basicinfomanage.wsmanage.model.WsModel", {
    extend: "Ext.data.Model",
    fields: [{
        name: "sid",
        type: "string"
    }, {
        name: "serviceName",
        type: "string"
    }, {
        name: "remark"
    }, {
        name: "url"
    }, {
        name: "wsdlUrl"
    }, {
        name: "targetNamespace"
    }, {
        name: "method"
    }, {
        name: "output"
    }, {
        name: "version"
    }]
});