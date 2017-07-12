Ext.define("core.basicinfomanage.commonpeoplemanage.controller.CommonPeopleController",
    {
        extend: "Ext.app.Controller",
        mixins: {
            suppleUtil: "core.util.SuppleUtil",
            messageUtil: "core.util.MessageUtil",
            formUtil: "core.util.FormUtil"
        },
        init: function () {
            var self = this;

            this.control({

                "panel[xtype=deletecommonpeoplegrid]": {
                    select: this.deleteCheckEdit,
                    deselect: this.deleteCheckEdit
                },
                "deletecommonpeoplegrid button[ref=deletePeople]": {
                    click: function (btn) {
                        var grid = btn.up("panel[xtype=deletecommonpeoplegrid]");
                        var records = grid.getSelectionModel().getSelection();
                        var uids = new Array();
                        for (var i = 0; i < records.length; i++) {
                            uids.push(records[i].get('uid'));
                        }
                        Ext.Msg.confirm("用户禁用确认", "<center><h3>确定要禁用选中的用户吗？<h3></center>",
                            function (result) {
                                if (result == "yes") {
                                    var resObj = self
                                        .ajax({
                                            url: "commonuser/disableUser",
                                            params: {
                                                uids: uids.join(",")
                                            }
                                        });
                                    if (resObj.success) {
                                        grid.getStore().load();
                                        self.msgbox("禁用成功");
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
                },
                /**
                 * 查找人员信息
                 */
                "commonpeoplegrid button[ref=searchPeople]": {
                    click: function (btn) {
                        var tbar = btn.ownerCt;
                        var uid = tbar.down("textfield[name=uid]").getValue();
                        var name = tbar.down("textfield[name=account]").getValue();
                        var grid = tbar.ownerCt;
                        var _store = grid.getStore();
                        proxy = _store.getProxy();
                        proxy.extraParams = {
                            uid: uid,
                            name: name
                        };
                        _store.loadPage(1);
                        return false;
                    }
                },
                "deletecommonpeoplegrid button[ref=searchPeople]": {
                    click: function (btn) {
                        var tbar = btn.ownerCt;
                        var uid = tbar.down("textfield[name=uid]").getValue();
                        var name = tbar.down("textfield[name=account]").getValue();
                        var grid = tbar.ownerCt;
                        var _store = grid.getStore();
                        proxy = _store.getProxy();
                        proxy.extraParams = {
                            uid: uid,
                            name: name
                        };
                        _store.loadPage(1);
                        return false;
                    }
                }


            });
            return false;
        },
        views: ["core.basicinfomanage.commonpeoplemanage.view.CommonPeopleGrid",
            "core.basicinfomanage.commonpeoplemanage.view.DeleteCommonPeopleGrid"],
        stores: ["core.basicinfomanage.commonpeoplemanage.store.CommonPeopleStore"],
        models: ["core.basicinfomanage.peoplemanage.model.PeopleModel"],
        deleteCheckEdit: function () {
            var grid = Ext.ComponentQuery.query("panel[xtype=deletecommonpeoplegrid]")[0];
            var num = grid.getSelectionModel().getSelection().length;
            var deletePeople = Ext.ComponentQuery.query("panel[xtype=deletecommonpeoplegrid] button[ref=deletePeople]")[0];
            if (deletePeople != null) {
                deletePeople.setDisabled(num == 0);
            }
        }
    });