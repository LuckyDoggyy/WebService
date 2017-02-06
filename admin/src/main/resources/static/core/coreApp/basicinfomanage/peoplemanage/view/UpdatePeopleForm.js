Ext.define("core.kaoqinmanager.peoplemanage.view.UpdatePeopleForm", {
	extend : "Ext.form.Panel",
	alias : "widget.updatepeopleform",
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
					allowBlank : false,// 不允许为空
					blankText : '工号不能为空'// 错误提示内容
				}, {
					fieldLabel : '部门名称',
					xtype : "combobox",
					anchor : '-5',
					tabIndex : 3,
					name : 'deptbh',
					readOnly:true,
					queryMode : 'remote', // 这个需要加上。。
					store : "core.kaoqinmanager.departmentmanage.store.DeptOptStore",
					loadingText : '正在加载数据，请稍侯……',
					triggerAction : 'all',
					valueField : 'deptbh',
					forceSelection : true,
					blankText : '部门名称不能为空',
					allowBlank : false,// 不允许为空
					displayField : 'deptname'/*
												 * , listeners : { 'select' :
												 * function() { //
												 * console.log(this); var
												 * deptname = this.rawValue; var
												 * deptbh = this.value; //
												 * console.log(deptbh); var
												 * addpeopleform = this
												 * .up("panel[xtype=addpeopleform]");
												 * addpeopleform .down(
												 * "textfield[name=deptname]")
												 * .setValue(deptname); var
												 * banzu = addpeopleform
												 * .down("textfield[name=banzubh]");
												 * banzu .getStore() .load( {
												 * params : { deptbh : deptbh },
												 * callback : function( records,
												 * operation, success) { //
												 * console.log("load!!!"); },
												 * scope : this }); return
												 * false; } }
												 */
				}, {
					name : 'sex',
					fieldLabel : '性别',
					anchor : '-5',
					xtype : "combobox",
					tabIndex : 5,
					editable : false,
					queryMode : "local",
					forceSelection : true, // 只允许从下拉列表中进行选择，不能输入文本
					displayField : 'text',
					valueField : 'value',
					value : '男',
					store : Ext.create('Ext.data.ArrayStore', {
								fields : ['text', 'value'],
								data : [['男', '男'], ['女', '女']]
							})
				}, {
					xtype : 'textfield',
					fieldLabel : '地址',
					anchor : '-5',
					tabIndex : 7,
					name : 'useraddress',
					allowBlank : true
					// 允许为空

			}]
	}, {
		items : [{
					xtype : 'textfield',
					fieldLabel : '姓名' + '<font color=red>*</font>',
					anchor : '-5',
					tabIndex : 2,
					name : 'username',
					allowBlank : false,// 不允许为空
					blankText : '工号不能为空'// 错误提示内容
				}, {

					xtype : 'textfield',
					fieldLabel : '职务',
					anchor : '-5',
					tabIndex : 4,
					name : 'userjob',
					allowBlank : true

				}, {

					xtype : 'textfield',
					fieldLabel : '联系方式',
					anchor : '-5',
					tabIndex : 6,
					name : 'usertel',
					allowBlank : true

				}, {

					xtype : 'textfield',
					fieldLabel : '备注信息',
					anchor : '-5',
					tabIndex : 8,
					name : 'remarks',
					allowBlank : true

				} /*
					 * { fieldLabel : '班组名称', xtype : "combobox", anchor : '-5',
					 * name : 'banzubh', queryMode :
					 * 'local',//因为上面的那个combobox已经把此处的store得到了，直接使用就好了 store :
					 * "core.kaoqinmanager.peoplemanage.store.BanzuOptStore",
					 * loadingText : '正在加载数据，请稍侯……', triggerAction : 'all',
					 * valueField : 'banzubh', forceSelection : true, allowBlank :
					 * false,// 不允许为空 displayField : 'banzuname', listeners:{
					 * 'change':function(){ var banzuname=this.rawValue; var
					 * addpeopleform=this.up("panel[xtype=addpeopleform]");
					 * addpeopleform.down("textfield[name=banzuname]").setValue(banzuname);
					 * return false; } } },
					 */]
	}],
	buttons : [{
				text : '修改',
				ref : 'updatePeople',
				iconCls : "table_save"
			}, {
				text : '返回',
				ref : "return",
				iconCls : "return"

			}]
});