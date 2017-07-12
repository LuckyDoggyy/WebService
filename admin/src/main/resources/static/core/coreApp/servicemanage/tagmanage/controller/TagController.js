Ext.define("core.servicemanage.tagmanage.controller.TagController",
    {
        extend: "Ext.app.Controller",
        mixins: {
            suppleUtil: "core.util.SuppleUtil",
            messageUtil: "core.util.MessageUtil",
            formUtil: "core.util.FormUtil",
            treeUtil: "core.util.TreeUtil"
        },
        init: function () {
            var self = this;
            this.control({
                "panel[xtype=deletetaggrid]": {
                    select: this.deleteCheckEdit,
                    deselect: this.deleteCheckEdit
                },
                "panel[xtype=updatetaggrid]": {
                    select: this.updateCheckEdit,
                    deselect: this.updateCheckEdit
                },

                "panel[xtype=addtag] button[ref=addTag]": {
                    click: function (btn) {
                        var addtag = btn.up("panel[xtype=addtag]");
                        var formObj = addtag.getForm();
                        var params = self.getFormValue(formObj);
                        if (params.orderIndex == null || params.orderIndex == "") {
                            params.orderIndex = 0
                        }
                        if (formObj.isValid()) {
                            var resObj = self.ajax({
                                url: "flowTag/addTag",
                                params: params
                            });
                            if (resObj.success) {
                                self.msgbox(resObj.obj);
                                addtag.down("textfield[name=tagName]").reset();
                                addtag.down("textfield[name=orderIndex]").reset();
                                return false;
                            } else {
                                Ext.Msg.alert("友情提示", resObj.obj);
                                return false;
                            }
                        } else {
                            Ext.Msg.alert('友情提示', "请检查添加的数据");
                            return false;
                        }
                        return false;
                    }
                },

                "panel[xtype=updatetaggrid] button[ref=updateTag]": {
                    click: function (btn) {
                        var grid = btn.up("panel[xtype=updatetaggrid]");
                        var records = grid.getSelectionModel().getSelection();
                        if (records.length != 1) {
                            Ext.Msg.alert('提示', '请选择一个分类！');
                            return false;
                        }
                        ;
                        var window = Ext.create('Ext.window.Window', {
                            title: '修改分类信息',
                            height: 340,
                            width: 580,
                            constrain: true,
                            maximizable: true,
                            layout: 'fit',
                            fixed: true,
                            modal: true,
                            items: {
                                xtype: 'updatetagform',
                                id: 'updatetagform'
                            }
                        });
                        var form = window.down("panel[xtype=updatetagform]");
                        form.loadRecord(records[0]);
                        window.show();
                        return false;
                    }
                },

                "panel[xtype=updatetagform] button[ref=updateTag]": {
                    click: function (btn) {
                        var updatetagform = btn.up("panel[xtype=updatetagform]");
                        var formObj = updatetagform.getForm();
                        var params = self.getFormValue(formObj);
                        if (params.orderIndex == null || params.orderIndex == "") {
                            params.orderIndex = 0
                        }
                        if (formObj.isValid()) {
                            var resObj2 = self.ajax({
                                url: "flowTag/updateTag",
                                params: params
                            });
                            if (resObj2.success) {
                                self.msgbox(resObj2.obj);
                                Ext.ComponentQuery.query("panel[xtype=updatetaggrid] component[xtype=pagingtoolbar]")[0].moveFirst();
                                btn.ownerCt.ownerCt.ownerCt.close();
                                return false;
                            } else {
                                Ext.Msg.alert("友情提示", resObj.obj);
                                return false;
                            }
                        } else {
                            Ext.Msg.alert('友情提示', "请检查要修改分类的数据");
                            return false;
                        }
                        return false;
                    }
                },

                "deletetaggrid button[ref=deleteTag]": {
                    click: function (btn) {
                        var grid = btn.up("panel[xtype=deletetaggrid]");
                        var records = grid.getSelectionModel().getSelection();
                        var autoids = new Array();
                        for (var i = 0; i < records.length; i++) {
                            autoids.push(records[i].get('autoId'));
                        }
                        Ext.Msg.confirm("分类信息删除确认", "<center><h3>确定要删除选中的分类信息吗？<h3></center>",
                            function (result) {
                                if (result == "yes") {
                                    var resObj = self
                                        .ajax({
                                            url: "flowTag/disableTag",
                                            params: {
                                                autoids: autoids.join(",")
                                            }
                                        });
                                    if (resObj.success) {
                                        grid.getStore().load();
                                        self.msgbox(resObj.obj);
                                        return false;
                                    } else {
                                        Ext.Msg.alert('友情提示', resObj.obj);
                                        return false;
                                    }
                                    return false;
                                } else {
                                    return false;
                                }
                            });
                    }
                }

            });
        },
        views: ["core.servicemanage.tagmanage.view.AddTag",
            "core.servicemanage.tagmanage.view.UpdateTagForm",
            "core.servicemanage.tagmanage.view.DeleteTagGrid",
            "core.servicemanage.tagmanage.view.UpdateTagGrid",
            "core.servicemanage.tagmanage.view.TagGrid"],
        stores: ["core.servicemanage.tagmanage.store.TagStore"],
        models: ["core.servicemanage.tagmanage.model.TagModel"],
        deleteCheckEdit: function () {
            var grid = Ext.ComponentQuery.query("panel[xtype=deletetaggrid]")[0];
            var num = grid.getSelectionModel().getSelection().length;
            var deleteMenu = Ext.ComponentQuery.query("panel[xtype=deletetaggrid] button[ref=deleteTag]")[0];
            if (deleteMenu != null) {
                deleteMenu.setDisabled(num == 0);
            }
        },
        updateCheckEdit: function () {
            var grid = Ext.ComponentQuery.query("panel[xtype=updatetaggrid]")[0];
            var num = grid.getSelectionModel().getSelection().length;
            var updateMenu = Ext.ComponentQuery.query("panel[xtype=updatetaggrid] button[ref=updateTag]")[0];
            if (updateMenu != null) {
                updateMenu.setDisabled(num != 1);
            }
        }
    });