Ext.define("core.basicinfomanage.wsmanage.view.AddWsForm2", {
	extend : "Ext.grid.Panel",
	alias : "widget.addwsform2",

	selModel : {
		selType : "checkboxmodel",
		mode : 'SIMPLE'
	},
	layout : 'anchor',
	defaults : {
		anchor : '100%'
	},
	align : 'center',
	autoScroll : true,
	buttonAlign : 'center',
	dockedItems : [{
		xtype : 'toolbar',
		dock : 'top',
		items : [{
			xtype : 'textfield',
			fieldLabel : '工号',
			labelWidth : 30,
			width : 120,
			name : 'usergh'
		}, {
			xtype : 'textfield',
			fieldLabel : '姓名',
			labelWidth : 30,
			width : 120,
			name : 'username'
		}, {
			xtype : 'button',
			text : '查询',
			iconCls : 'search',
			ref : 'searchPeople'
		}, {
			xtype : 'button',
			text : '添加所选人员到班组',
			ref : 'addToSelectedBanzu'
        }, {
			xtype : 'datefield',
			fieldLabel : '考勤初始日期',
			labelWidth : 90,
			padding : '5,20,5,30',
			// value:new Date(),
			name : 'kaoqinstarttime',
			format : 'Y-m-d',
			allowBlank : true
		}]
	}],
	columns : [{
				xtype : 'rownumberer',
				width : 50,
				height : 36,
				text : '序号',
				align : 'center'
			}, {
				text : "工号",
				dataIndex : "usergh",
				width : 130
			}, {
				text : "姓名",
				dataIndex : "username",
				width : 140
			}, {
				text : "部门编号",
				dataIndex : "deptbh",
				width : 140
			}, {
				text : "部门名称",
				dataIndex : "deptname",
				width : 140
			}, {
				text : "班组编号",
				dataIndex : "banzubh",
				width : 140
			}, {
				text : "班组名称",
				dataIndex : "banzuname",
				width : 140
			}, {
				text : "职务",
				dataIndex : "userjob",
				width : 140
			}, {
				text : "联系方式",
				dataIndex : "usertel",
				width : 140
			}, {
				text : "地址",
				dataIndex : "useraddress",
				width : 140
			}, {
				text : "备注",
				dataIndex : "remarks",
				width : 140
			}],
	store : "core.kaoqinmanager.peoplemanage.store.PeopleStore",
	bbar : [{
				xtype : 'button',
				text : '全选',
				handler : function(button, e) {
					button.ownerCt.ownerCt.getSelectionModel().selectAll();
				}
			}, {
				xtype : 'button',
				text : '取消',
				handler : function(button, e) {
					button.ownerCt.ownerCt.getSelectionModel().deselectAll();
				}
			}, {
				xtype : 'pagingtoolbar',
				store : "core.kaoqinmanager.peoplemanage.store.PeopleStore",
				displayInfo : true,
				flex : 1
			}]

});