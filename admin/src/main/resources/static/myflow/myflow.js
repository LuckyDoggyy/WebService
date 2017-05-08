(function(b) {
    var a = {};     //  a flow
    a.config = {    //  状态
        editable: true,
        lineHeight: 15,
        basePath: "",
        rect: {     //      节点
            attr: {
                x: 10,
                y: 10,
                width: 100,
                height: 50,
                r: 5,
                fill: "90-#fff-#C0C0C0",
                stroke: "#000",
                "stroke-width": 1
            },
            showType: "image&text",    //   iamge,text,image$text
            type: "state",
            name: {
                text: "state",
                "font-style": "italic"
            },
            text: {
                text: "状态",
                "font-size": 13
            },
            margin: 5,
            props: [],
            img: {}
        },
        path: {     //    路径
            attr: {
                path: {
                    path: "M10 10L100 100",
                    stroke: "#808080",
                    fill: "none",
                    "stroke-width": 2
                },
                arrow: {
                    path: "M10 10L10 10",
                    stroke: "#808080",
                    fill: "#808080",
                    "stroke-width": 2,
                    radius: 4
                },
                fromDot: {      //      起始点
                    width: 5,
                    height: 5,
                    stroke: "#fff",
                    fill: "#000",
                    cursor: "move",
                    "stroke-width": 2
                },
                toDot: {        //  终止点
                    width: 5,
                    height: 5,
                    stroke: "#fff",
                    fill: "#000",
                    cursor: "move",
                    "stroke-width": 2
                },
                bigDot: {
                    width: 5,
                    height: 5,
                    stroke: "#fff",
                    fill: "#000",
                    cursor: "move",
                    "stroke-width": 2
                },
                smallDot: {
                    width: 5,
                    height: 5,
                    stroke: "#fff",
                    fill: "#000",
                    cursor: "move",
                    "stroke-width": 3
                }
            },
            text: {         //      TO to节点
                text: "TO {to}",
                cursor: "move",
                background: "#000"
            },
            textPos: {      //      文本位置
                x: 0,
                y: -10
            },
            props: {        //      路径属性
                text: {
                    name: "text",
                    label: "显示",
                    value: "",
                    editor: function() {
                        return new a.editors.textEditor()
                    }
                }
            }
        },
        tools: {            //      工具栏
            attr: {
                left: 10,
                top: 10
            },
            pointer: {},
            path: {},
            states: {},
            save: {         //      保存按钮  c:json代码
                onclick: function(c) {
                    alert(c)
                }
            }
        },
        props: {        //      属性编辑器
            attr: {
                top: 10,
                right: 30
            },
            props: {}
        },
        restore: "",
        activeRects: {      //      当前激活状态
            rects: [],
            rectAttr: {
                stroke: "#ff0000",
                "stroke-width": 2
            }
        },
        historyRects: {     //      历史激活状态
            rects: [],
            pathAttr: {
                path: {
                    stroke: "#00ff00"
                },
                arrow: {
                    stroke: "#00ff00",
                    fill: "#00ff00"
                }
            }
        }
    };


    a.util = {          //    flow a 的组件


        isLine: function(g, f, e) {     //      三点是否在一条直线上
            var d, c;
            if ((g.x - e.x) == 0) {
                d = 1
            } else {
                d = (g.y - e.y) / (g.x - e.x)
            }
            c = (f.x - e.x) * d + e.y;
            if ((f.y - c) < 10 && (f.y - c) > -10) {
                f.y = c;
                return true
            }
            return false
        },


        center: function(d, c) {                //      选取中点
            return {
                x: (d.x - c.x) / 2 + c.x,
                y: (d.y - c.y) / 2 + c.y
            }
        },
        nextId: (function() {
            var c = 0;
            return function() {
                return++c
            }
        })(),


        connPoint: function(j, d) {     //      计算矩形中心到p的连线与矩形的交叉点
            var c = d,
                e = {
                    x: j.x + j.width / 2,
                    y: j.y + j.height / 2
                };
            //      计算正切角度
            var l = (e.y - c.y) / (e.x - c.x);
            l = isNaN(l) ? 0 : l;
            var k = j.height / j.width;

            // 计算箭头位置
            var h = c.y < e.y ? -1 : 1,
                f = c.x < e.x ? -1 : 1,
                g,
                i;

            // 按角度判断箭头位置
            if (Math.abs(l) > k && h == -1) {
                g = e.y - j.height / 2;
                i = e.x + h * j.height / 2 / l
            } else {
                if (Math.abs(l) > k && h == 1) {
                    g = e.y + j.height / 2;
                    i = e.x + h * j.height / 2 / l
                } else {
                    if (Math.abs(l) < k && f == -1) {
                        g = e.y + f * j.width / 2 * l;
                        i = e.x - j.width / 2
                    } else {
                        if (Math.abs(l) < k && f == 1) {
                            g = e.y + j.width / 2 * l;
                            i = e.x + j.width / 2
                        }
                    }
                }
            }
            return {
                x: i,
                y: g
            }
        },

        //      画箭头，p1 开始位置,p2 结束位置, r前头的边长
        arrow: function(l, k, d) {
            var g = Math.atan2(l.y - k.y, k.x - l.x) * (180 / Math.PI);
            var h = k.x - d * Math.cos(g * (Math.PI / 180));
            var f = k.y + d * Math.sin(g * (Math.PI / 180));
            var e = h + d * Math.cos((g + 120) * (Math.PI / 180));
            var j = f - d * Math.sin((g + 120) * (Math.PI / 180));
            var c = h + d * Math.cos((g + 240) * (Math.PI / 180));
            var i = f - d * Math.sin((g + 240) * (Math.PI / 180));
            return [k, {
                x: e,
                y: j
            },
                {
                    x: c,
                    y: i
                }]
        }
    };


    a.rect = function(p, m) {
        var u = this,
            g = "rect" + a.util.nextId(),
            E = b.extend(true, {},
                a.config.rect, p),
            C = m,      //  raphael画笔
            t,      //  矩形
            e,      //  图标
            n,      //  状态名称
            f,      //  显示文本
            x,v;      //拖动时,保存起点位置
        t = C.rect(E.attr.x, E.attr.y, E.attr.width, E.attr.height, E.attr.r).hide().attr(E.attr);
        e = C.image(a.config.basePath + E.img.src, E.attr.x + E.img.width / 2, E.attr.y + (E.attr.height - E.img.height) / 2, E.img.width, E.img.height).hide();

        //  文本
        n = C.text(E.attr.x + E.img.width + (E.attr.width - E.img.width) / 2, E.attr.y + a.config.lineHeight / 2, E.name.text).hide().attr(E.name);
        f = C.text(E.attr.x + E.img.width + (E.attr.width - E.img.width) / 2, E.attr.y + (E.attr.height - a.config.lineHeight) / 2 + a.config.lineHeight, E.text.text).hide().attr(E.text);


        //      矩形拖动处理
        t.drag(function(r, o) {
                A(r, o)    //drag move
            },
            function() {
                z()     //  drag start
            },
            function() {
                l()     //  drag up
            });

        //      图标拖动
        e.drag(function(r, o) {
                A(r, o)
            },
            function() {
                z()
            },
            function() {
                l()
            });

        //      名字拖动
        n.drag(function(r, o) {
                A(r, o)
            },
            function() {
                z()
            },
            function() {
                l()
            });

        //      文本拖动
        f.drag(function(r, o) {
                A(r, o)
            },
            function() {
                z()
            },
            function() {
                l()
            });


        var A = function(F, r) {        //拖动中  位置
            if (!a.config.editable) {
                return
            }
            var o = (x + F);
            var G = (v + r);
            q.x = o - E.margin;
            q.y = G - E.margin;
            B()
        };
        var z = function() {        //开始拖动  半透明
            x = t.attr("x");
            v = t.attr("y");
            t.attr({
                opacity: 0.5
            });
            e.attr({
                opacity: 0.5
            });
            f.attr({
                opacity: 0.5
            })
        };
        var l = function() {        //拖动结束   恢复透明度
            t.attr({
                opacity: 1
            });
            e.attr({
                opacity: 1
            });
            f.attr({
                opacity: 1
            })
        };

        //  改变大小的边框     s:_bpath    i:bdots
        var s, i = {},
            h = 5,
            q = {       //  _bbox
                x: E.attr.x - E.margin,
                y: E.attr.y - E.margin,
                width: E.attr.width + E.margin * 2,
                height: E.attr.height + E.margin * 2
            };
        s = C.path("M0 0L1 1").hide();    //  _bpath = _r.path

        //  上
        i.t = C.rect(0, 0, h, h).attr({
            fill: "#000",
            stroke: "#fff",
            cursor: "s-resize"
        }).hide().drag(function(r, o) {
                D(r, o, "t")
            },
            function() {
                k(this.attr("x") + h / 2, this.attr("y") + h / 2, "t")
            },
            function() {});

        //  改变大小的点
        //  左上
        i.lt = C.rect(0, 0, h, h).attr({
            fill: "#000",
            stroke: "#fff",
            cursor: "nw-resize"
        }).hide().drag(function(r, o) {
                D(r, o, "lt")
            },
            function() {
                k(this.attr("x") + h / 2, this.attr("y") + h / 2, "lt")
            },
            function() {});

        //  左
        i.l = C.rect(0, 0, h, h).attr({
            fill: "#000",
            stroke: "#fff",
            cursor: "w-resize"
        }).hide().drag(function(r, o) {
                D(r, o, "l")
            },
            function() {
                k(this.attr("x") + h / 2, this.attr("y") + h / 2, "l")
            },
            function() {});

        //  左下
        i.lb = C.rect(0, 0, h, h).attr({
            fill: "#000",
            stroke: "#fff",
            cursor: "sw-resize"
        }).hide().drag(function(r, o) {
                D(r, o, "lb")
            },
            function() {
                k(this.attr("x") + h / 2, this.attr("y") + h / 2, "lb")
            },
            function() {});

        //  下
        i.b = C.rect(0, 0, h, h).attr({
            fill: "#000",
            stroke: "#fff",
            cursor: "s-resize"
        }).hide().drag(function(r, o) {
                D(r, o, "b")
            },
            function() {
                k(this.attr("x") + h / 2, this.attr("y") + h / 2, "b")
            },
            function() {});

        //  右下
        i.rb = C.rect(0, 0, h, h).attr({
            fill: "#000",
            stroke: "#fff",
            cursor: "se-resize"
        }).hide().drag(function(r, o) {
                D(r, o, "rb")
            },
            function() {
                k(this.attr("x") + h / 2, this.attr("y") + h / 2, "rb")
            },
            function() {});

        //  右
        i.r = C.rect(0, 0, h, h).attr({
            fill: "#000",
            stroke: "#fff",
            cursor: "w-resize"
        }).hide().drag(function(r, o) {
                D(r, o, "r")
            },
            function() {
                k(this.attr("x") + h / 2, this.attr("y") + h / 2, "r")
            },
            function() {});


        //  右上
        i.rt = C.rect(0, 0, h, h).attr({
            fill: "#000",
            stroke: "#fff",
            cursor: "ne-resize"
        }).hide().drag(function(r, o) {
                D(r, o, "rt")
            },
            function() {
                k(this.attr("x") + h / 2, this.attr("y") + h / 2, "rt")
            },
            function() {});


        //      根据情况判定大小及位置改动
        var D = function(F, r, G) {         //       var bdragMove = function(dx, dy, t)
            if (!a.config.editable) {
                return
            }
            var o = _bx + F,
                H = _by + r;
            switch (G) {
                case "t":
                    q.height += q.y - H;
                    q.y = H;
                    break;
                case "lt":
                    q.width += q.x - o;
                    q.height += q.y - H;
                    q.x = o;
                    q.y = H;
                    break;
                case "l":
                    q.width += q.x - o;
                    q.x = o;
                    break;
                case "lb":
                    q.height = H - q.y;
                    q.width += q.x - o;
                    q.x = o;
                    break;
                case "b":
                    q.height = H - q.y;
                    break;
                case "rb":
                    q.height = H - q.y;
                    q.width = o - q.x;
                    break;
                case "r":
                    q.width = o - q.x;
                    break;
                case "rt":
                    q.width = o - q.x;
                    q.height += q.y - H;
                    q.y = H;
                    break
            }
            B()         //      resize()
        };
        var k = function(r, o, F) {
            _bx = r;
            _by = o
        };


        //          事件处理         $([_rect.node, _text.node, _name.node, _img.node]).bind('click',
        b([t.node, f.node, n.node, e.node]).bind("click",
            function() {
                if (!a.config.editable) {
                    return
                }
                w();
                var o = b(C).data("mod");
                switch (o) {
                    case "pointer":
                        break;
                    case "path":
                        var r = b(C).data("currNode");
                        if (r && r.getId() != g && r.getId().substring(0, 4) == "rect") {
                            b(C).trigger("addpath", [r, u])
                        }
                        break
                }
                b(C).trigger("click", u);
                b(C).data("currNode", u);
                return false
            });
        var j = function(o, r) {
            if (!a.config.editable) {
                return
            }
            if (r.getId() == g) {
                b(C).trigger("showprops", [E.props, r])         //     点击展示属性
            } else {
                d()
            }
        };
        b(C).bind("click", j);
        var c = function(o, F, r) {
            if (r.getId() == g) {
                f.attr({
                    text: F        //       更改文本内容
                })
            }
        };
        b(C).bind("textchange", c);


        //      私有函数，边框路径
        function y() {
            return "M" + q.x + " " + q.y + "L" + q.x + " " + (q.y + q.height) + "L" + (q.x + q.width) + " " + (q.y + q.height) + "L" + (q.x + q.width) + " " + q.y + "L" + q.x + " " + q.y
        }       //      path字符串

        //      显示边框
        function w() {      //      根据rect边框上的顶点
            s.show();
            for (var o in i) {
                i[o].show()
            }
        }

        //      隐藏边框
        function d() {      //      隐藏边框上的顶点
            s.hide();
            for (var o in i) {
                i[o].hide()
            }
        }

        //      根据_bbox更新位置信息
        function B() {
            var F = q.x + E.margin,
                r = q.y + E.margin,
                G = q.width - E.margin * 2,
                o = q.height - E.margin * 2;
            t.attr({
                x: F,
                y: r,
                width: G,
                height: o
            });
            switch (E.showType) {
                case "image":
                    e.attr({
                        x:
                        F + (G - E.img.width) / 2,
                        y: r + (o - E.img.height) / 2
                    }).show();      //      展示图片
                    break;
                case "text":
                    t.show();
                    f.attr({
                        x:
                        F + G / 2,
                        y: r + o / 2
                    }).show();      //      展示内容
                    break;
                case "image&text":
                    t.show();
                    n.attr({
                        x:
                        F + E.img.width + (G - E.img.width) / 2,
                        y: r + a.config.lineHeight / 2
                    }).show();
                    f.attr({
                        x: F + E.img.width + (G - E.img.width) / 2,
                        y: r + (o - a.config.lineHeight) / 2 + a.config.lineHeight
                    }).show();
                    e.attr({
                        x: F + E.img.width / 2,
                        y: r + (o - E.img.height) / 2
                    }).show();
                    break
            }
            i.t.attr({
                x: q.x + q.width / 2 - h / 2,
                y: q.y - h / 2
            });
            i.lt.attr({
                x: q.x - h / 2,
                y: q.y - h / 2
            });
            i.l.attr({
                x: q.x - h / 2,
                y: q.y - h / 2 + q.height / 2
            });
            i.lb.attr({
                x: q.x - h / 2,
                y: q.y - h / 2 + q.height
            });
            i.b.attr({
                x: q.x - h / 2 + q.width / 2,
                y: q.y - h / 2 + q.height
            });
            i.rb.attr({
                x: q.x - h / 2 + q.width,
                y: q.y - h / 2 + q.height
            });
            i.r.attr({
                x: q.x - h / 2 + q.width,
                y: q.y - h / 2 + q.height / 2
            });
            i.rt.attr({
                x: q.x - h / 2 + q.width,
                y: q.y - h / 2
            });
            s.attr({        //  _bpath
                path: y()
            });
            b(C).trigger("rectresize", u)
        }

        //  rect转化为json字符串
        this.toJson = function() {
            var r = "{type:'" + E.type + "',text:{text:'" + f.attr("text") + "'}, attr:{ x:"
                + Math.round(t.attr("x")) + ", y:" + Math.round(t.attr("y")) + ", width:"
                + Math.round(t.attr("width")) + ", height:" + Math.round(t.attr("height"))
                + "}, props:{";
            for (var o in E.props) {
                r += o + ":{value:'" + E.props[o].value + "'},"
            }
            if (r.substring(r.length - 1, r.length) == ",") {
                r = r.substring(0, r.length - 1)
            }
            r += "}}";
            return r
        };

        //  从数据中恢复图
        this.restore = function(o) {
            var r = o;
            E = b.extend(true, E, o);
            f.attr({
                text: r.text.text
            });
            B()
        };
        this.getBBox = function() {
            return q
        };
        this.getId = function() {
            return g
        };
        this.remove = function() {
            t.remove();
            f.remove();
            n.remove();
            e.remove();
            s.remove();
            for (var o in i) {
                i[o].remove()
            }
        };

        //  设置文本内容
        this.text = function() {
            return f.attr("text")
        };
        this.attr = function(o) {       //  o:attr
            if (o) {
                t.attr(o)
            }
        };
        B()     //  resize()
    };
    a.path = function(q, n, u, e) {        // myflow.path = function(o, r, from, to)
        var v = this,
            z = n,
            B = b.extend(true, {},
                a.config.path),
            i,
            t,
            f,
            h = B.textPos,
            y,
            w,
            k = u,
            s = e,
            g = "path" + a.util.nextId(),
            x;
        function p(G, H, D, L) {
            var F = this,
                M = G,
                r, o = D,       //缓存移动前时的位置
                O = L,
                K, I, N = H;        //缓存位置信息，计算出的中点
            switch (M) {
                case "from":
                    r = z.rect(H.x - B.attr.fromDot.width / 2, H.y - B.attr.fromDot.height / 2, B.attr.fromDot.width, B.attr.fromDot.height).attr(B.attr.fromDot);
                    break;
                case "big":
                    r = z.rect(H.x - B.attr.bigDot.width / 2, H.y - B.attr.bigDot.height / 2, B.attr.bigDot.width, B.attr.bigDot.height).attr(B.attr.bigDot);
                    break;
                case "small":
                    r = z.rect(H.x - B.attr.smallDot.width / 2, H.y - B.attr.smallDot.height / 2, B.attr.smallDot.width, B.attr.smallDot.height).attr(B.attr.smallDot);
                    break;
                case "to":
                    r = z.rect(H.x - B.attr.toDot.width / 2, H.y - B.attr.toDot.height / 2, B.attr.toDot.width, B.attr.toDot.height).attr(B.attr.toDot);
                    break
            }
            if (r && (M == "big" || M == "small")) {
                r.drag(function(Q, P) {     //  初始化拖动
                        C(Q, P)
                    },
                    function() {
                        J()
                    },
                    function() {
                        E()
                    });
                var C = function(R, Q) {       //拖动中
                    var P = (K + R),
                        S = (I + Q);
                    F.moveTo(P, S)
                };
                var J = function() {     //开始拖动
                    if (M == "big") {
                        K = r.attr("x") + B.attr.bigDot.width / 2;
                        I = r.attr("y") + B.attr.bigDot.height / 2
                    }
                    if (M == "small") {
                        K = r.attr("x") + B.attr.smallDot.width / 2;
                        I = r.attr("y") + B.attr.smallDot.height / 2
                    }
                };
                var E = function() {}       //拖动结束
            }
            this.type = function(P) {
                if (P) {
                    M = P
                } else {
                    return M
                }
            };
            this.node = function(P) {
                if (P) {
                    r = P
                } else {
                    return r
                }
            };
            this.left = function(P) {
                if (P) {
                    o = P
                } else {
                    return o
                }
            };
            this.right = function(P) {
                if (P) {
                    O = P
                } else {
                    return O
                }
            };
            this.remove = function() {
                o = null;
                O = null;
                r.remove()
            };
            this.pos = function(P) {
                if (P) {
                    N = P;
                    r.attr({
                        x: N.x - r.attr("width") / 2,
                        y: N.y - r.attr("height") / 2
                    });
                    return this
                } else {
                    return N
                }
            };
            this.moveTo = function(Q, T) {
                this.pos({
                    x: Q,
                    y: T
                });
                switch (M) {
                    case "from":        //  起始点
                        if (O && O.right() && O.right().type() == "to") {
                            O.right().pos(a.util.connPoint(s.getBBox(), N))
                        }
                        if (O && O.right()) {
                            O.pos(a.util.center(N, O.right().pos()))
                        }
                        break;


                    case "big":         //      大点：起点、终点、中点
                        if (O && O.right() && O.right().type() == "to") {
                            O.right().pos(a.util.connPoint(s.getBBox(), N))
                        }
                        if (o && o.left() && o.left().type() == "from") {
                            o.left().pos(a.util.connPoint(k.getBBox(), N))
                        }
                        if (O && O.right()) {
                            O.pos(a.util.center(N, O.right().pos()))
                        }
                        if (o && o.left()) {
                            o.pos(a.util.center(N, o.left().pos()))
                        }


                        var S = {       //三个大点在一条线上，移除中间小点
                            x: N.x,
                            y: N.y
                        };
                        if (a.util.isLine(o.left().pos(), S, O.right().pos())) {
                            M = "small";
                            r.attr(B.attr.smallDot);
                            this.pos(S);
                            var P = o;
                            o.left().right(o.right());
                            o = o.left();
                            P.remove();
                            var R = O;
                            O.right().left(O.left());
                            O = O.right();
                            R.remove()
                        }
                        break;
                    case "small":       // 移动小点时，转变为大点，增加俩个小点   移动中点时，出现折点
                        if (o && O && !a.util.isLine(o.pos(), {
                                    x: N.x,
                                    y: N.y
                                },
                                O.pos())) {
                            M = "big";
                            r.attr(B.attr.bigDot);
                            var P = new p("small", a.util.center(o.pos(), N), o, o.right());
                            o.right(P);
                            o = P;
                            var R = new p("small", a.util.center(O.pos(), N), O.left(), O);
                            O.left(R);
                            O = R
                        }
                        break;
                    case "to":      //      终点
                        if (o && o.left() && o.left().type() == "from") {
                            o.left().pos(a.util.connPoint(k.getBBox(), N))
                        }
                        if (o && o.left()) {
                            o.pos(a.util.center(N, o.left().pos()))
                        }
                        break
                }
                m()
            }
        }
        function j() {       // function dotList()  if(!_from) throw '没有from节点!'
            var D, C, E = k.getBBox(),      //      getBBox获得起始的rect
                F = s.getBBox(),
                r,
                o;
            r = a.util.connPoint(E, {
                x: F.x + F.width / 2,
                y: F.y + F.height / 2
            });
            o = a.util.connPoint(F, r);
            D = new p("from", r, null, new p("small", {
                x: (r.x + o.x) / 2,
                y: (r.y + o.y) / 2
            }));
            D.right().left(D);
            C = new p("to", o, D.right(), null);
            D.right().right(C);


            //      转换为path格式字符串
            this.toPathString = function() {
                if (!D) {
                    return ""
                }
                var J = D,
                    I = "M" + J.pos().x + " " + J.pos().y,      //线的路径
                    H = "";
                while (J.right()) {
                    J = J.right();
                    I += "L" + J.pos().x + " " + J.pos().y
                }

                //  箭头路径，箭头的方向
                var G = a.util.arrow(J.left().pos(), J.pos(), B.attr.arrow.radius);
                H = "M" + G[0].x + " " + G[0].y + "L" + G[1].x + " " + G[1].y + "L" + G[2].x + " " + G[2].y + "z";
                return [I, H]
            };
            this.toJson = function() {
                var G = "[",
                    H = D;
                while (H) {
                    if (H.type() == "big") {
                        G += "{x:" + Math.round(H.pos().x) + ",y:" + Math.round(H.pos().y) + "},"
                    }
                    H = H.right()
                }
                if (G.substring(G.length - 1, G.length) == ",") {
                    G = G.substring(0, G.length - 1)
                }
                G += "]";
                return G
            };
            this.restore = function(H) {
                var I = H,
                    J = D.right();
                for (var G = 0; G < I.length; G++) {
                    J.moveTo(I[G].x, I[G].y);
                    J.moveTo(I[G].x, I[G].y);
                    J = J.right()
                }
                this.hide()
            };
            this.fromDot = function() {
                return D
            };
            this.toDot = function() {
                return C
            };
            this.midDot = function() {      // 返回中间点
                var H = D.right(),
                    G = D.right().right();
                while (G.right() && G.right().right()) {
                    G = G.right().right();
                    H = H.right()
                }
                return H
            };
            this.show = function() {
                var G = D;
                while (G) {
                    G.node().show();
                    G = G.right()
                }
            };
            this.hide = function() {
                var G = D;
                while (G) {
                    G.node().hide();
                    G = G.right()
                }
            };
            this.remove = function() {
                var G = D;
                while (G) {
                    if (G.right()) {
                        G = G.right();
                        G.left().remove()
                    } else {
                        G.remove();
                        G = null
                    }
                }
            }
        }


        //      初始化操作
        B = b.extend(true, B, q);
        i = z.path(B.attr.path.path).attr(B.attr.path);
        t = z.path(B.attr.arrow.path).attr(B.attr.arrow);
        x = new j();
        x.hide();
        f = z.text(0, 0, B.text.text).attr(B.text).attr({
            text: B.text.text.replace("{from}", k.text()).replace("{to}", s.text())
        });
        f.drag(function(r, o) {
                if (!a.config.editable) {
                    return
                }
                f.attr({
                    x: y + r,
                    y: w + o
                })
            },
            function() {
                y = f.attr("x");
                w = f.attr("y")
            },
            function() {
                var o = x.midDot().pos();
                h = {
                    x: f.attr("x") - o.x,
                    y: f.attr("y") - o.y
                }
            });
        m();          // 初始化路径refreshpath();


        //事件处理
        b([i.node, t.node]).bind("click",
            function() {
                if (!a.config.editable) {
                    return
                }
                b(z).trigger("click", v);
                b(z).data("currNode", v);
                return false
            });

        // 处理点击事件，线或矩形
        var l = function(r, C) {
            if (!a.config.editable) {
                return
            }
            if (C && C.getId() == g) {
                x.show();
                b(z).trigger("showprops", [B.props, v])     //      点击显示属性框
            } else {
                x.hide()
            }
            var o = b(z).data("mod");
            switch (o) {
                case "pointer":
                    break;
                case "path":
                    break
            }
        };
        b(z).bind("click", l);


        // 删除事件处理
        var A = function(o, r) {
            if (!a.config.editable) {
                return
            }
            if (r && (r.getId() == k.getId() || r.getId() == s.getId())) {
                b(z).trigger("removepath", v)
            }
        };
        b(z).bind("removerect", A);

        // 矩形移动时间处理
        var d = function(C, D) {
            if (!a.config.editable) {
                return
            }
            if (k && k.getId() == D.getId()) {
                var o;
                if (x.fromDot().right().right().type() == "to") {
                    o = {
                        x: s.getBBox().x + s.getBBox().width / 2,
                        y: s.getBBox().y + s.getBBox().height / 2
                    }
                } else {
                    o = x.fromDot().right().right().pos()
                }
                var r = a.util.connPoint(k.getBBox(), o);
                x.fromDot().moveTo(r.x, r.y);
                m()
            }
            if (s && s.getId() == D.getId()) {
                var o;
                if (x.toDot().left().left().type() == "from") {
                    o = {
                        x: k.getBBox().x + k.getBBox().width / 2,
                        y: k.getBBox().y + k.getBBox().height / 2
                    }
                } else {
                    o = x.toDot().left().left().pos()
                }
                var r = a.util.connPoint(s.getBBox(), o);
                x.toDot().moveTo(r.x, r.y);
                m()
            }
        };
        b(z).bind("rectresize", d);



        var c = function(r, o, C) {
            if (C.getId() == g) {   // 改变自身文本
                f.attr({
                    text: o
                })
            }
        };
        b(z).bind("textchange", c);
        this.from = function() {
            return k
        };
        this.to = function() {
            return s
        };

        //      path转化为json数据
        this.toJson = function() {
            var r = "{from:'" + k.getId() + "',to:'" + s.getId() + "', dots:" + x.toJson() + ",text:{text:'" + f.attr("text") + "'},textPos:{x:" + Math.round(h.x) + ",y:" + Math.round(h.y) + "}, props:{";
            for (var o in B.props) {
                r += o + ":{value:'" + B.props[o].value + "'},"
            }
            if (r.substring(r.length - 1, r.length) == ",") {
                r = r.substring(0, r.length - 1)
            }
            r += "}}";
            return r
        };

        //      恢复
        this.restore = function(o) {
            var r = o;
            B = b.extend(true, B, o);
            x.restore(r.dots)
        };

        //      删除
        this.remove = function() {
            x.remove();
            i.remove();
            t.remove();
            f.remove();
            try {
                b(z).unbind("click", l)
            } catch(o) {}
            try {
                b(z).unbind("removerect", A)
            } catch(o) {}
            try {
                b(z).unbind("rectresize", d)
            } catch(o) {}
            try {
                b(z).unbind("textchange", c)
            } catch(o) {}
        };


        // 刷新路径
        function m() {
            var r = x.toPathString(),
                o = x.midDot().pos();
            i.attr({
                path: r[0]
            });
            t.attr({
                path: r[1]
            });
            f.attr({
                x: o.x + h.x,
                y: o.y + h.y
            })
        }
        this.getId = function() {
            return g
        };
        this.text = function() {
            return f.attr("text")
        };
        this.attr = function(o) {
            if (o && o.path) {
                i.attr(o.path)
            }
            if (o && o.arrow) {
                t.attr(o.arrow)
            }
        }
    };



    a.props = function(h, f) {
        var j = this,
            c = b("#myflow_props").hide().draggable({
                handle: "#myflow_props_handle"
            }).resizable().css(a.config.props.attr).bind("click",
                function() {
                    return false
                }),
            e = c.find("table"),        //  table表单
            g = f,
            i;


        var d = function(n, m, o) {     // showpropshandler
            if (i && i.getId() == o.getId()) {
                return
            }
            i = o;
            b(e).find(".editor").each(function() {      //      连续点击不刷新  b:$
                var k = b(this).data("editor");
                if (k) {
                    k.destroy()
                }
            });
            e.empty();
            c.show();
            for (var l in m) {        //    展示属性
                e.append('<tr><th>' + m[l].label + '</th><td><div id="p' + l + '" class="editor"></div></td></tr>');
                if (m[l].editor) {
                    m[l].editor().init(m, l, "p" + l, o, g)
                }
            }
            e.append('<tr id="myflowDelTR"><th>删除</th><td><input type="button" value="删除" onclick="if(confirm(\'确认删除？！\'))jQuery(document).trigger(\'keydown\',true);"/></td></tr>');

            /*
            e.append('<tr id="myflowAddTR"><th>添加</th><td><input type="button" value="添加" id="myflowAddButton" onclick="b("#myflowAddButton").trigger(\'click\');"/></td></tr>');
            b('#myflowAddButton').click(function(){
                b('#myflowAddTR').before('<tr><th><input style="width:100%" id="templabel"></th><td><input style="width:100%" id="tempvalue"></div></td><td><input type="button" id="tempButton" value="确定" onclick=""></td></tr>');
            });
            b('#tempButton').click(function(){

            });
            */
        };

        b(g).bind("showprops", d)
    };



    //属性编辑器
    a.editors = {

        textEditor: function() {    //textEditor()方法
            var d, e, c, g, f;
            this.init = function(i, h, m, l, j) {       //   _props = props;_k = k;_div = div;_src = src;_r = r;
                d = i;
                e = h;
                c = m;
                g = l;
                f = j;
                b('<input  style="width:100%;"/>').val(g.text()).change(function() {        //  <input>表单
                    i[e].value = b(this).val();
                    b(f).trigger("textchange", [b(this).val(), g])
                }).appendTo("#" + c);
                b("#" + c).data("editor", this)
            };
            this.destroy = function() {
                b("#" + c + " input").each(function() {
                    d[e].value = b(this).val();
                    b(f).trigger("textchange", [b(this).val(), g])
                })
            }
        }




    };


    //      初始化流程
    a.init = function(x, r) {
        var v = b(window).width(),
            e = b(window).height(),
            y = Raphael(x, v * 1.5, e * 1.5),
            q = {},
            g = {};
        b.extend(true, a.config, r);


        /**
         * 删除： 删除状态时，触发removerect事件，连接在这个状态上当路径监听到这个事件，触发removepath删除自身；
         * 删除路径时，触发removepath事件
         */

      /*  b(document).click(function(){       //add props
            $(this)prepend('<tr><th><input style='width:100%' id='templabel'></th><td><input style='width:100%' id='tempvalue'></div></td></tr>');

            }
        });*/

        b(document).keydown(function (i,byButton) {     //delete删除
            if (!a.config.editable) {
                return
            }
            if (i.keyCode == 46||byButton) {    //
                var j = b(y).data("currNode");
                if (j) {
                    if (j.getId().substring(0, 4) == "rect") {      //根据id删除rect
                        b(y).trigger("removerect", j)
                    } else {
                        if (j.getId().substring(0, 4) == "path") {  //根据id删除path
                            b(y).trigger("removepath", j)
                        }
                    }
                    b(y).removeData("currNode")
                }
            }
        });


        b(document).click(function () {
                b(y).data("currNode", null);
                b(y).trigger("click", {
                    getId: function () {
                        return "00000000"
                    }
                });
                b(y).trigger("showprops", [a.config.props.props, {
                    getId: function () {
                        return "00000000"
                    }
                }])
            });


        //      删除事件
        var w = function(c, i) {
            if (!a.config.editable) {
                return
            }
            if (i.getId().substring(0, 4) == "rect") {
                q[i.getId()] = null;
                i.remove()
            } else {
                if (i.getId().substring(0, 4) == "path") {
                    g[i.getId()] = null;
                    i.remove()
                }
            }
        };
        b(y).bind("removepath", w);
        b(y).bind("removerect", w);


        //添加状态
        b(y).bind("addrect",
            function(j, c, k) {
                var i = new a.rect(b.extend(true, {},
                    a.config.tools.states[c], k), y);
                q[i.getId()] = i
            });


        //添加路径
        var f = function(i, k, j) {
            var c = new a.path({},
                y, k, j);
            g[c.getId()] = c
        };
        b(y).bind("addpath", f);

        //模式
        b(y).data("mod", "point");
        if (a.config.editable) {
            //工具栏
            b("#myflow_tools").draggable({
                handle: "#myflow_tools_handle"
            }).css(a.config.tools.attr);
            b("#myflow_tools .node").hover(function() {
                    b(this).addClass("mover")
                },
                function() {
                    b(this).removeClass("mover")
                });
            b("#myflow_tools .selectable").click(function() {
                b(".selected").removeClass("selected");
                b(this).addClass("selected");
                b(y).data("mod", this.id)
            });
            b("#myflow_tools .state").each(function() {
                b(this).draggable({
                    helper: "clone"
                })
            });
            b(x).droppable({
                accept: ".state",
                drop: function(c, i) {
                    b(y).trigger("addrect", [i.helper.attr("type"), {
                        attr: {
                            x: i.helper.offset().left,
                            y: i.helper.offset().top
                        }
                    }])
                }
            });


            //保存
            b("#myflow_save").click(function() {
                var i = "{states:{";
                for (var c in q) {
                    if (q[c]) {
                        i += q[c].getId() + ":" + q[c].toJson() + ","
                    }
                }
                if (i.substring(i.length - 1, i.length) == ",") {
                    i = i.substring(0, i.length - 1)
                }
                i += "},paths:{";
                for (var c in g) {
                    if (g[c]) {
                        i += g[c].getId() + ":" + g[c].toJson() + ","
                    }
                }
                if (i.substring(i.length - 1, i.length) == ",") {
                    i = i.substring(0, i.length - 1)
                }
                i += "},props:{props:{";
                for (var c in a.config.props.props) {
                    i += c + ":{value:'" + a.config.props.props[c].value + "'},"
                }
                if (i.substring(i.length - 1, i.length) == ",") {
                    i = i.substring(0, i.length - 1)
                }
                i += "}}}";
                a.config.tools.save.onclick(i)
            });

            //      属性框
            new a.props({},
                y)
        }


        //恢复
        if (r.restore) {
            var B = r.restore;
            var z = {};
            if (B.states) {
                for (var s in B.states) {
                    var d = new a.rect(b.extend(true, {},
                        a.config.tools.states[B.states[s].type], B.states[s]), y);
                    d.restore(B.states[s]);
                    z[s] = d;
                    q[d.getId()] = d
                }
            }
            if (B.paths) {
                for (var s in B.paths) {
                    var n = new a.path(b.extend(true, {},
                        a.config.tools.path, B.paths[s]), y, z[B.paths[s].from], z[B.paths[s].to]);
                    n.restore(B.paths[s]);
                    g[n.getId()] = n
                }
            }
        }

        //历史状态
        var A = a.config.historyRects,
            l = a.config.activeRects;
        if (A.rects.length || l.rects.length) {
            var m = {},
                z = {};
            for (var h in g) {
                if (!z[g[h].from().text()]) {
                    z[g[h].from().text()] = {
                        rect: g[h].from(),
                        paths: {}
                    }
                }
                z[g[h].from().text()].paths[g[h].text()] = g[h];
                if (!z[g[h].to().text()]) {
                    z[g[h].to().text()] = {
                        rect: g[h].to(),
                        paths: {}
                    }
                }
            }
            for (var u = 0; u < A.rects.length; u++) {
                if (z[A.rects[u].name]) {
                    z[A.rects[u].name].rect.attr(A.rectAttr)
                }
                for (var t = 0; t < A.rects[u].paths.length; t++) {
                    if (z[A.rects[u].name].paths[A.rects[u].paths[t]]) {
                        z[A.rects[u].name].paths[A.rects[u].paths[t]].attr(A.pathAttr)
                    }
                }
            }
            for (var u = 0; u < l.rects.length; u++) {
                if (z[l.rects[u].name]) {
                    z[l.rects[u].name].rect.attr(l.rectAttr)
                }
                for (var t = 0; t < l.rects[u].paths.length; t++) {
                    if (z[l.rects[u].name].paths[l.rects[u].paths[t]]) {
                        z[l.rects[u].name].paths[l.rects[u].paths[t]].attr(l.pathAttr)
                    }
                }
            }
        }
    };


    //  添加jquery方法
    b.fn.myflow = function(c) {
        return this.each(function() {
            a.init(this, c)
        })
    };
    b.myflow = a
})(jQuery);