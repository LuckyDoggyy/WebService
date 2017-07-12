Ext.define("core.servicemanage.bpelmanage.view.FlowTagGrid", {
    extend: "Ext.grid.Panel",
    alias: "widget.flowtaggrid",
    selModel: {
        selType: "checkboxmodel",
        mode: 'SIMPLE'
    },
    multiSelect: true,
    columnLines: true,
    loadMask: {
        msg: "数据加载中，请稍等..."
    },
    autoScroll: true,
    disableSelection: false,
    enableKeyNav: true,
    dockedItems: [{
        xtype: 'toolbar',
        dock: 'top',
        items: [
            {
                xtype: 'button',
                text: '删除',
                iconCls: 'search',
                ref: 'disable'
            }
        ]
    }],
    columns: [{
        width: 80,
        height: 36,
        dataIndex: "autoid",
        text: '编号',
        align: 'center'
    }, {
        text: "分类id",
        dataIndex: "tagid",
        width: 200,
        align: 'center'
    }, {
        text: "分类名",
        dataIndex: "tagName",
        width: 140,
        align: 'center'
    }],
    store: "core.servicemanage.bpelmanage.store.FlowTagStore",
    bbar: [{
        xtype: 'button',
        text: '全选',
        handler: function (button, e) {
            button.ownerCt.ownerCt.getSelectionModel().selectAll();
        }
    }, {
        xtype: 'button',
        text: '取消',
        handler: function (button, e) {
            button.ownerCt.ownerCt.getSelectionModel().deselectAll();
        }
    }, {
        xtype: 'pagingtoolbar',
        store: "core.servicemanage.bpelmanage.store.FlowTagStore",
        displayInfo: true,
        flex: 1
    }]

});