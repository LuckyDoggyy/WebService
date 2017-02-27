Ext.define("core.systemmanage.rolemanage.view.UpdateRoleForm", {
	extend : "Ext.form.Panel",
	alias : "widget.updateroleform",
	bodyPadding : '5 5 0',
    width : 600,
	layout : 'anchor',
	defaults : {
		anchor : '100%'
	},
	align : 'center',
	buttonAlign : 'center',
	items : [{
				xtype : 'textfield',
				name : 'rid',
				hidden : true
			},{
				xtype : 'textfield',
				fieldLabel : '角色名' + '<font color=red>*</font>',
				labelWidth : 85,
				name : 'rname',
				allowBlank : false,
				blankText : '角色名不能为空'
			}],
	buttons : [{
				text : '修改',
				ref : 'updateRole',
				iconCls : "table_save"
			}],
	initComponent : function() {
			this.callParent(arguments);
		}
});