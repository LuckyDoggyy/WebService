Ext.define("core.kaoqinmanager.peoplemanage.view.ChangeGangWeiForm", {
	extend : "Ext.form.Panel",
	alias : "widget.changegangweiform",
	bodyPadding : '5 5 0',
	width : 600,
	buttonAlign : 'center',
	fieldDefaults : {
		labelAlign : 'top',
		msgTarget : 'side'
	},
	defaults : {
		border : false,
		xtype : 'panel',
		flex : 1,
		layout : 'anchor'
	},
	layout : 'hbox',
	items : [{
		items : [{
					xtype : 'textfield',
					fieldLabel : '工号' + '<font color=red>*</font>',
					anchor : '-5',
					tabIndex : 1,
					name : 'usergh',
					readOnly : true,
					allowBlank : false,
					blankText : '工号不能为空'
				}, {
					fieldLabel : '部门名称',
					xtype : "combobox",
					anchor : '-5',
					tabIndex : 3,
					name : 'deptbh',
					queryMode : 'remote', // 这个需要加上。。
					store : "core.kaoqinmanager.departmentmanage.store.DeptOptStore",
					loadingText : '正在加载数据，请稍侯……',
					triggerAction : 'all',
					valueField : 'deptbh',
					forceSelection : true,
					blankText : '部门名称不能为空',
					allowBlank : false,// 不允许为空
					displayField : 'deptname',
					listeners : {
						'select' : function() {
							// console.log(this);
							var deptbh = this.value;
							// console.log(deptbh);
							var banzubhcombobox = this.ownerCt.ownerCt
									.down("combobox[name=banzubh]");
							banzubhcombobox.getStore().load({
								params : {
									deptbh : deptbh
								},
								callback : function(records, operation, success) {
									 //console.log("load!!! selecy ");
								},
								scope : this
							});
							return false;
						}/*,
						"change" : function(newValue, oldValue, eOpts) {
							
							var banzubhcombobox = this.ownerCt.ownerCt
									.down("combobox[name=banzubh]");
							banzubhcombobox.getStore().load({
								params : {
									deptbh : newValue
								},
								callback : function(records, operation, success) {
								  console.log("load!!! change");
								},
								scope : this
							});
							return false;
						}*/
					}
				}, {
					xtype : 'datefield',
					fieldLabel : '调离日期' + '<font color=red>*</font>',
					anchor : '-5',
					// tabIndex : 1,
					value : new Date(),
					name : 'diaolidate',
					format : 'Y-m-d',
					// maxValue : Ext.Date.parse("2015-04-01", "Y-m-d"),
					allowBlank : false,// 不允许为空
					blankText : '调离日期不能为空'// 错误提示内容
				}]
	}, {
		items : [{
					xtype : 'textfield',
					fieldLabel : '姓名' + '<font color=red>*</font>',
					anchor : '-5',
					tabIndex : 2,
					name : 'username',
					readOnly : true,
					allowBlank : false,
					blankText : '工号不能为空'
				}, {
					fieldLabel : '班组',
					xtype : "combobox",
					anchor : '-5',
					name : 'banzubh',
					queryMode : 'local',
					store : "core.kaoqinmanager.peoplemanage.store.AllBanzuOptStore",
					loadingText : '正在加载数据，请稍侯……',
					triggerAction : 'all',
					valueField : 'banzubh',
					forceSelection : true,
					allowBlank : false,
					blankText : '班组不能为空',
					displayField : 'banzuname',
					width : 160

				}, {
					xtype : 'textfield',
					fieldLabel : '备注',
					anchor : '-5',
					tabIndex : 5,
					name : 'remarks',
					allowBlank : true

				}]
	}],
	buttons : [{
				text : '修改',
				ref : 'changeGangwei',
				iconCls : "table_save"
			}, {
				text : '返回',
				ref : "return",
				iconCls : "return"

			}]
});