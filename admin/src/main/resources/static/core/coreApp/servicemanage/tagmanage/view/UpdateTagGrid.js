Ext.define("core.servicemanage.tagmanage.view.UpdateTagGrid", {
    extend: "Ext.grid.Panel",
    alias: "widget.updatetaggrid",
    title: "<center height=40>分类修改</center>",
    selModel: {
        selType: "checkboxmodel",
        mode: 'SIMPLE'
    },
    multiSelect: true,
    columnLines: true, // 展示竖线
    loadMask: {
        msg: "数据加载中，请稍等..."
    },
    autoScroll: true,
    disableSelection: false,
    enableKeyNav: true,
    dockedItems: [{
        xtype: 'toolbar',
        dock: 'top',
        height: 36,
        items: [{
            xtype: 'button',
            text: '修改分类',
            ref: 'updateTag',
            iconCls: 'modify',
            disabled: true
        }]
    }],
    columns: [{
        text: "分类编号",
        dataIndex: "autoId",
        align: 'center',
        width: 150
    }, {
        text: "分类名",
        dataIndex: "tagName",
        align: 'center',
        width: 120
    }],
    store: "core.servicemanage.tagmanage.store.TagStore",
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
        store: "core.servicemanage.tagmanage.store.TagStore",
        displayInfo: true,
        flex: 1
    }]

});