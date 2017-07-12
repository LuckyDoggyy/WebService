Ext.define("core.servicemanage.bpelmanage.view.AllTagGrid", {
    extend: "Ext.grid.Panel",
    alias: "widget.alltaggrid",
    title: "<center height=40>所有分类</center>",
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
        items: [{
            fieldLabel: '分类',
            xtype: "combobox",
            name: 'pid',
            labelWidth: 35,
            emptyText: '请选择分类',
            queryMode: 'remote',
            store: "core.servicemanage.tagmanage.store.TagOptStore",
            loadingText: '正在加载数据，请稍侯……',
            triggerAction: 'all',
            valueField: 'value',
            forceSelection: false,
            displayField: 'name'
        }, {
            xtype: 'button',
            text: '查询',
            iconCls: 'search',
            ref: 'searchTag'
        }, {
            xtype: 'button',
            text: '添加分类',
            iconCls: 'search',
            ref: 'addTag'
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