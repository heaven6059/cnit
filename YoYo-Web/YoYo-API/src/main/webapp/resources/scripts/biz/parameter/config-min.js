wd.scrollFloat = function (id, pos, css) {
    var self = this,
        obj = this.dom.id(id),
        objWH = {
            'w': this.css(obj, 'width').replace(/px/gi, ''),
            'h': this.css(obj, 'height').replace(/px/gi, '')
        },
        posXY = this.abs.point(this.dom.id(pos)),
        tempObj = this.dom.id('config_nav_temp'),
        tempId = '_' + id,
        createFloat = function () {
            var div = self.dom.create('div');
            div.id = tempId;
            div.className = obj.className;
            div.style.cssText = obj.style.cssText;
            div.innerHTML = obj.innerHTML;

            self.css(div, {
                'top': '0px',
                'left': '0px',
                'display': 'none',
                'position': self.browsers.isIE6 ? 'absolute' : 'fixed'
            });

            if (css) self.css(div, css);

            document.body.appendChild(div);
        },
        setTop = function () {
            var scrollXY = self.abs.scroll();
            posXY = self.abs.point(self.dom.id(pos));
            if (scrollXY.top > posXY.y) {
                if (self.browsers.isIE6)
                    self.css(obj, { 'position': 'absolute', 'top': scrollXY.top + 'px', 'left': posXY.x + 'px' });
                else {
                    self.css(obj, { 'position': 'fixed', 'left': (posXY.x - scrollXY.left) + 'px', 'top': '0px' });
                    self.css(tempObj, 'display', 'block');
                }
                setHideOwnerPrices('block');
            }
            else {
                self.css(obj, { 'position': 'relative', 'left': '0px', 'top': '0px' });
                self.css(tempObj, 'display', 'none');
                setHideOwnerPrices('none');
            }
        },
        setLeft = function () {
            var scrollXY = self.abs.scroll();
            posXY = self.abs.point(self.dom.id(pos));
            if (scrollXY.left > posXY.x) {
                if (self.browsers.isIE6)
                    self.css(obj, { 'position': 'absolute', 'display': 'block', 'top': '0px', 'left': ((scrollXY.left - posXY.x) + 'px') });
                else
                    self.css(obj, { 'position': 'fixed', 'display': 'block', 'left': '0px', 'top': ((posXY.y - scrollXY.top) + 'px') });
            }
            else {
                self.css(obj, 'display', 'none');
            }
        },
        setAbs = function () {
            var scrollXY = self.abs.scroll(),
                div = self.dom.id(tempId);
            posXY = self.abs.point(self.dom.id(pos));
            if (scrollXY.left > posXY.x) {
                if (self.browsers.isIE6)
                    self.css(div, { 'display': 'block', 'top': (scrollXY.top == 0 ? (scrollXY.top + posXY.y) : (scrollXY.top < posXY.y ? posXY.y : scrollXY.top)) + 'px', 'left': scrollXY.left + 'px', 'border': '1px solid #D9E5F3', 'fontSize': '12px' });
                else
                    self.css(div, { 'display': 'block', 'top': (scrollXY.top == 0 ? posXY.y : (scrollXY.top < posXY.y ? posXY.y - scrollXY.top : 0)) + 'px', 'left': '0px', 'border': '1px solid #D9E5F3', 'fontSize': '12px' });
            }
            else
                self.css(div, 'display', 'none');
        };

    this.top = function (isRun) {
        if (isRun) {
            setTop();
        } else {
            self.handler.addEvent(window, 'scroll', setTop);
            self.handler.addEvent(window, 'resize', setTop);
            self.handler.addEvent(window, 'load', setTop);
        }
    };

    this.left = function () {
        self.handler.addEvent(window, 'scroll', setLeft);
        self.handler.addEvent(window, 'resize', setLeft);
        self.handler.addEvent(window, 'load', setLeft);
        setLeft();
    };

    this.absTop = function (isRun) {
        if (isRun) {
            if (wd.browsers.isIE6) {
                self.css(this.dom.id(tempId), { 'top': '0' });
            }
            setAbs();
        }
        else {
            if (!this.dom.id(tempId)) createFloat();
            self.handler.addEvent(window, 'scroll', setAbs);
            self.handler.addEvent(window, 'resize', setAbs);
            self.handler.addEvent(window, 'load', setAbs);
        }
    };

    return this;
};

function setHideOwnerPrices(css) {
    var parent = wd.dom.id('config_nav'),
        elems = wd.dom.byClass('sys_ow', parent, 'span'),
        len = elems.length,
        i = 0;
    for (; i < len; i++)
        elems[i].style.display = css;
};

var paramConfig = function (seriesId, sid, cityId) {

    var self = this,
        template = {
            nav: function () {
                var retstr = new Array();
                retstr.push('<table cellspacing="0" cellpadding="0" class="tbset">');
                retstr.push('<tbody>');
                retstr.push('<tr>');
                retstr.push('<th>');
                retstr.push('<div id="config_setbox" class="setbox">');
                retstr.push('<label><input type="checkbox" value="high" ' + (self.Data.high ? 'checked="checked"' : '') + ' name="radioShow">高亮显示差异参数</label>');
                retstr.push('<label><input type="checkbox" value="hide" ' + (self.Data.hide ? 'checked="checked"' : '') + '  name="radioShow">隐藏相同参数</label>');
                retstr.push('<p>注：●标配  ○选配  -无</p>');
                retstr.push('</div>');
                retstr.push('</th>');
                retstr.push('#LIST#');
                retstr.push('</tr>');
                retstr.push('</tbody>');
                retstr.push('</table>');
                return retstr.join('');
            }
        },
        configNav = wd.dom.id('config_nav'),
        configData = wd.dom.id('config_data'),
        configSide = wd.dom.id('config_side');

    //缓存数据
    this.Data = {
        carList: [], //当前车型ID列表
        allList: [], //全部车型列表
        engine: [], //发动机列表
        syear: [],
        transmission: [], //变速箱列表
        carStruct: [], //车身结构列表
        carNum: 0,  //车型数量
        hide: false, //是否隐藏相同
        high: false, //是否高亮
        isPust: true, //是否刷新 发动机列表 变速箱列表 车身结构列表
        dealerPrice: {}, //经销商报价
        allowancePrice: {},//国家/地方补贴
        colorList: {}, //颜色列表
        innerColorList: {}, //内饰颜色列表
        userClickTR: [], //记录用户点击过的行
        keyLink: {}, //标题对应链接地址
        isCheckBox: false,
        curSpecCss: '#F0F3F8',
        twolCell: {
            '发动机特有技术': 0,
            '前悬架类型': 0,
            '后悬架类型': 0
        },
        navMeto: {
            isAdd: true,
            item: []
        }
    };

    //初始化
    this.init = function () {
        //检测是否有数据
        if (!config) return;

        this.isTop();        
		
        this.responseNav();

        this.responseContent();

        this.scrollFloat();

        this.selectEvent();
		
        this.MoveOrDel();

        this.checkboxInit();

        this.Data.navMeto.isAdd = false; //锁定

        this.repsonseNavScrollLeft();

        this.navScrollLeft();
    };


    //初始化浮动
    this.scrollFloat = function (l) {
        if (l) {
            //IE修正位置
            if (wd.browsers.isIE6) {
                var l = document["documentElement"].scrollLeft + document["body"].scrollLeft,
                    t = document.body.scrollHeight,
                    w = document["documentElement"].scrollTop + document["body"].scrollTop;
                if (t < w) {
                    setTimeout(function () {
                        var scrollXY = wd.abs.scroll();
                        posXY = wd.abs.point(wd.dom.id('content'));
                        wd.css(wd.dom.id('config_nav'), { 'position': 'absolute', 'top': scrollXY.top + 'px', 'left': posXY.x + 'px' });

                    }, 100);
                    window.scrollTo(l, t);
                }
            }
            wd.scrollFloat('config_side', 'config_data').left();
            wd.scrollFloat('config_nav', 'content').top(true);
            wd.scrollFloat('config_setbox', 'content').absTop(true);
        }
        else {
            wd.scrollFloat('config_side', 'config_data').left();
            wd.scrollFloat('config_nav', 'content').top();
            wd.scrollFloat('config_setbox', 'content').absTop();
        }
    };

    //输出导航
    this.responseNav = function (selectIds) {
        var paramesLen = arguments.length,
            spaceColNum = 0,
            html = [],
            items = config.result.paramtypeitems[0].paramitems[0].valueitems,
            ownerPrices = config.result.paramtypeitems[0].paramitems[1].valueitems,
            len = items.length,
            i = 0;


        this.Data.carList = [];
        this.Data.allList = [];

        //获取写入cookies的车系车型id
        var SpecList = null;
        SpecList = window.Car && window.Car.Cookie.readCookie(Car.Compare.cookieName);

        for (; i < len; i++) {
            this.Data.allList.push(items[i].specid);

            if (paramesLen == 1 && this.Data.isCheckBox && selectIds.indexOf('-' + i + '-') == -1) continue;

            html.push('<td>');
            html.push('<div class="btn_delbar">');
            html.push('<span class="sys_ow" style="display:none;">门店价格：' + (ownerPrices[i].value == '0.00万' || ownerPrices[i].value == '-' ? '暂无报价' : ownerPrices[i].value) + '</span>');
            html.push('<a target="_self" rel="' + items[i].specid + '" href="javascript:void(0);" class="btn_del"></a>');
            html.push('</div>');
			
            html.push('<div class="carbox">');
            html.push('<div><a href="' + items[i].specid + '/">' + items[i].value + '</a></div>');
            html.push('<p>');
            html.push('<a target="_self" href="javascript:void(0);"  rel="' + items[i].specid + '" class="switch_left">左移</a>');						            	
			if (SpecList != null && SpecList.indexOf(items[i].specid) >= 0) {
				html.push('<a data-toggle="comparepop" name="specBtnName" id="ASS_comparepop_' + items[i].specid + '" target="_self" href="javascript:void(0);" class="added"  data-select="1"><i class="icon10 icon10-plus"></i>已加入</a>');
			} else {                    
				html.push('<a data-toggle="comparepop" onclick="buildCarList('+items[i].specid+',\''+items[i].value+'\',\''+items[i].seriesId+'\',\''+items[i].brandId+'\');" name="specBtnName" id="ASS_comparepop_' + items[i].specid + '" target="_self" href="javascript:void(0);"  class="btn btn-mini"><i class="icon10 icon10-plus"></i>对比</a>');                    
            }            
            html.push('<a target="_self" href="javascript:void(0);"  rel="' + items[i].specid + '" class="switch_right">右移</a>');
            html.push('</p>');
            html.push('</div>');
            html.push('</td>');

            this.Data.carList.push(items[i].specid);
            this.Data.syear.push(items[i].value);
        }
        spaceColNum = this.Data.carList.length > 4 ? 0 : 4 - this.Data.carList.length;		
        for (var j = 0; j < spaceColNum; j++) {
            html.push('<td><div class="carbox"></div></td>');
        }

        this.Data.carNum = len;
        configNav.innerHTML = template.nav().replace('#LIST#', html.join(''));		
    };

    //是否显示移动按钮
    this.isShowMove = function () {
        var tds = wd.dom.tag('td', configNav),
            len = tds.length,
            i = 0,
            endNum = 0;

        for (; i < len; i++) {
            if (wd.dom.byClass('btn_del', tds[i]).length > 0) {
                wd.css(wd.dom.byClass('switch_left', tds[i], 'a')[0], 'display', '');
                wd.css(wd.dom.byClass('switch_right', tds[i], 'a')[0], 'display', '');
                if (i == 0)
                    wd.css(wd.dom.byClass('switch_left', tds[i], 'a')[0], 'display', 'none');
                endNum = i;
            }
        }
        if (wd.dom.byClass('switch_right', tds[endNum], 'a').length > 0)
            wd.css(wd.dom.byClass('switch_right', tds[endNum], 'a')[0], 'display', 'none');
    };

    //获取字符长度
    this.getCharactersLen = function (str) {
        var num = 0, i = 0, len;
        len = str.length;
        for (; i < len; i++) {
            var c = str.charCodeAt(i);
            if ((c >= 0x0001 && c <= 0x007e) || (0xff60 <= c && c <= 0xff9f)) {
                num++;
            } else {
                num += 2;
            }
        }
        return num;
    };

    //字符串处理，区分中文、英文
    this.getCharacters = {
        len: function (str) {
            var n = 0, i = 0, l;
            l = str.length;
            for (; i < l; i++) {
                var c = str.charCodeAt(i);
                if ((c >= 0x0001 && c <= 0x007e) || (0xff60 <= c && c <= 0xff9f)) {
                    n++;
                } else {
                    n += 2;
                }
            }
            return n;
        },
        sub: function (str, len) {
            return str.replace(/([\u0391-\uffe5])/ig, '$1a').substring(0, len).replace(/([\u0391-\uffe5])a/ig, '$1');
        }
    };

    //输出数据
    this.responseContent = function (selectIds) {

        //if (this.Data.carNum == 0) return;
        var sideHtml = [],
            conHtml = [],
            paramesLen = arguments.length,
            spaceColNum = this.Data.carList.length > 4 ? 0 : 4 - this.Data.carList.length,
            clickTR = this.Data.userClickTR.join(','),
            twolCell = this.Data.twolCell;

        sideHtml.push('<div style="display: none;" class="fdbox" id="config_side">');
        sideHtml.push('<table id="tab_side" cellspacing="0" cellpadding="0" class="tbcs">');
        sideHtml.push('<tbody>');

        var getTrHtml = function (items, idRang, isColor, rowNum, itemType, high) {

            //隐藏排量            
            var isTwols = twolCell[items.name] == 0 ? true : false;
            if (isColor) rowNum = 500 + rowNum;
            var titleLink = 'javascript:void(0);" target="_self',highlight = '';

            if (self.Data.high && !high) {
                highlight = 'class="highlight"';
            }

            //获取标题连接地址

            if (clickTR.indexOf('-' + rowNum + '-') == -1) {
                sideHtml.push('<tr ' + highlight + ' id="trs_' + rowNum + '">');
            }
            else {
                sideHtml.push('<tr style="background:#F0F3F8;"' + highlight + ' id="trs_' + rowNum + '">');
            }
            sideHtml.push('<th ' + (isTwols ? 'class="twol"' : '') + '>');
            sideHtml.push('<div><a href="' + titleLink + '">' + items.name + '</a></div>');
            sideHtml.push('</th>');
            sideHtml.push('</tr>');

            //输出一行数据
            if (clickTR.indexOf('-' + rowNum + '-') == -1) {
                conHtml.push('<tr ' + highlight + ' id="tr_' + rowNum + '">');
            }
            else {
                conHtml.push('<tr style="background:#F0F3F8;"' + highlight + ' id="tr_' + rowNum + '">');
            }

            conHtml.push('<th ' + (isTwols ? 'class="twol"' : '') + '>');
            conHtml.push('<div><a href="' + titleLink + '">' + items.name + '</a></div>');
            conHtml.push('</th>');
            var list = items.valueitems,
                strLen = 0;

            if (isColor) {
                for (var k = 0, llen = list.length; k < llen; k++) {
                    if (paramesLen == 1 && this.Data.isCheckBox && selectIds.indexOf('-' + k + '-') == -1) continue;
                    conHtml.push('<td ' + (list[k].specid == sid ? 'style="background:' + self.Data.curSpecCss + ';"' : '') + '><div class="fontf">' + list[k].value + '</div></td>');
                }
            }
            else {
                for (var k = 0, llen = list.length; k < llen; k++) {
                    if (paramesLen == 1 && this.Data.isCheckBox && selectIds.indexOf('-' + k + '-') == -1) continue;

                    if (self.Data.isPust) {
                        switch (items.name) {
                            
                            case '发动机':
                                self.Data.engine.push(list[k].value);
                                break;
                            case '变速箱':
                                self.Data.transmission.push(list[k].value);
                                break;
                            case '车身结构':
                                if (itemType != '0')
                                    self.Data.carStruct.push(list[k].value);
                                break;
                        }
                    }

					if (isTwols) {
                        conHtml.push('<td ' + (list[k].specid == sid ? 'style="background:' + self.Data.curSpecCss + ';"' : '') + '>');
                        if (list[k].value == '0') {
                            conHtml.push('<div>-</div>');
                        }
                        else {
                            strLen = self.getCharacters.len(list[k].value);
                            if (strLen > 44) {
                                conHtml.push('<div title="' + list[k].value + '">' + self.getCharacters.sub(list[k].value, 44) + '</div>');
                            }
                            else {
                                    conHtml.push('<div>' + list[k].value + '</div>');
							}
                        }
						conHtml.push('</td>');
                    }
                    else {
						conHtml.push('<td ' + (list[k].specid == sid ? 'style="background:' + self.Data.curSpecCss + ';"' : '') + '><div>' + (list[k].value == '0' ? '-' : list[k].value) + '</div></td>');
					}                    
                }
            }
            for (var i = 0; i < spaceColNum; i++) {
                conHtml.push('<td><div></div></td>');
            }
            conHtml.push('</tr>');
        },
        mager = function (sIndex, data, isColor, ids, attr) {

            var items = data,
                len = items.length,
                row = 0,
                titleClass = '';

            for (var i = 0; i < len; i++) {

                var idRang = i + ids;
                //输出大分类
                conHtml.push('<table id="tab_' + idRang + '" cellspacing="0" cellpadding="0" class="tbcs">');
                conHtml.push('<tbody>');

                sideHtml.push('<tr>');
                sideHtml.push('<th show="1" id="sth_' + idRang + '" class="cstitle" style="border-right:none;">');
                sideHtml.push('<h3><span>' + items[i].name + '</span></h3>');
                sideHtml.push('</th>');
                sideHtml.push('</tr>');

                conHtml.push('<tr>');
                conHtml.push('<th show="1" pid="tab_' + idRang + '" id="nav_meto_' + idRang + '" class="cstitle" colspan="' + ((this.Data.carList.length < 4 ? 4 : this.Data.carList.length) + 1) + '">');
                conHtml.push('<h3><span>' + items[i].name + '</span></h3>');
                conHtml.push('</th>');
                conHtml.push('</tr>');

                this.Data.navMeto.isAdd && this.Data.navMeto.item.push({ name: items[i].name, id: 'nav_meto_' + idRang });

                for (var j = (i == 0 ? sIndex : 0), klen = items[i][attr + 'items'].length; j < klen; j++) {

                    //隐藏相同参数
                    if (self.Data.hide) {
                        if (!self.compare(items[i][attr + 'items'][j].valueitems, selectIds))
                            getTrHtml(items[i][attr + 'items'][j], idRang, isColor, row, i);
                    }
                    else {
                        //高亮参数
                        if (self.Data.high) {
                            getTrHtml(items[i][attr + 'items'][j], idRang, isColor, row, i, self.compare(items[i][attr + 'items'][j].valueitems, selectIds));
                        }
                        else {
                            getTrHtml(items[i][attr + 'items'][j], idRang, isColor, row, i);
                        }
                    }

                    if (j == klen - 1) {
                        //输出颜色                      
                        conHtml.push('</tbody>');
                        conHtml.push('</table>');
                    }
                    row++;
                }
            }
        },


        getDealerPrice = function () {

            var items = config.result.paramtypeitems[0].paramitems[1].valueitems,
            	items2= config.result.paramtypeitems[0].paramitems[2].valueitems,
                len = items.length,
                i = 0,
                temp = [],
                tempAllowance = [];

            //加入经销商报价                       
            sideHtml.push('<tr style="background:#F0F3F8;" id="trs_111">');                    
			sideHtml.push('<tr style="background:#F0F3F8;" id="trs_222">');
            
            conHtml.push('<table cellspacing="0" cellpadding="0" class="tbcs">');
            conHtml.push('<tbody>');

            
            conHtml.push('<tr id="tr_111">');
            
            conHtml.push('<th><div>销售价</div></th>');
			
            for (; i < len; i++) {
                if (paramesLen == 1 && this.Data.isCheckBox && selectIds.indexOf('-' + i + '-') == -1) continue;
                conHtml.push('<td ' + (items[i].specid == sid ? 'style="background:' + self.Data.curSpecCss + ';"' : '') + '><div>' + (!items[i].value  || items[i].value == '-' ? '暂无报价' : (items[i].value/10000)+'万') + '</div></td>');
                temp.push('<td ' + (items2[i].specid == sid ? 'style="background:' + self.Data.curSpecCss + ';"' : '') + '><div>' + (!items2[i].value || items2[i].value == '-' ? '暂无报价' : (items2[i].value/10000)+'万') + '</div></td>');
            }

            for (var k = 0; k < spaceColNum; k++) {
                conHtml.push('<td><div></div></td>');
                temp.push('<td><div></div></td>');
                tempAllowance.push('<td><div></div></td>');
            }
            conHtml.push('</tr>');
            //国家/地方补贴
			conHtml.push('<tr id="tr_222">');            
            conHtml.push('<th><div>市场价</div></th>');
            conHtml.push(temp.join(''));
            conHtml.push('</tr>');
            conHtml.push('</tbody>');
            conHtml.push('</table>');
			
        };

        getDealerPrice();

        mager(2, config.result.paramtypeitems, false, 0, 'param');

        sideHtml.push('</tbody>');
        sideHtml.push('</table>');
        sideHtml.push('</div>');

        //刷新设置为false保证刷新一次
        self.Data.isPust = false;

        configData.innerHTML = sideHtml.join('') + conHtml.join('');

        self.titleEvent();
        self.mouseChangeCss();

        this.isShowMove();
    };

    //比较数据列 相同 true 不同 false
    this.compare = function (arr, selectIds) {

        var flag = true,
            len = arr.length,
            i = 0, t, isSet = true;

        if (len == 0) return flag;

        //t = arr[0].Value;

        for (; i < len; i++) {
            if (selectIds != undefined && this.Data.isCheckBox && selectIds.indexOf('-' + i + '-') == -1) continue;
            if (isSet) {
                t = arr[i].value;
                isSet = false;
                continue;
            }
            if (arr[i].value != t) {
                flag = false;
                break;
            }
        }

        return flag;
    };

    //标题显示隐藏事件
    this.titleEvent = function () {

        var ts = wd.dom.byClass('cstitle', configData, 'th'),
            tab = wd.dom.byClass('tbcs', configData, 'table'),
            len = ts.length,
            i = 0,
            self = this,
            conWidth = (parseInt(wd.css(wd.dom.tag('table', configNav)[0], 'width').replace(/px/gi, '')) + 1) + 'px';

        for (var j = 1, tlen = tab.length; j < tlen; j++) {
            wd.css(tab[j], { 'width': conWidth });
        }

        return;

        for (; i < len; i++) {
            ts[i].onclick = function () {
                var isShow = this.getAttribute('show'),
                    index = this.id.replace('sth_', '').replace('dth_', ''),
                    sObj = wd.dom.id('sth_' + index),
                    dObj = wd.dom.id('dth_' + index),
                    bcss = '',
                    show = 0;

                if (isShow == '1') {
                    bcss = 'setcon';
                    hideShow(index, 'none');
                }
                else {
                    hideShow(index, '');
                    show = 1;
                }

                wd.css(wd.dom.id('tab_' + index), { 'borderBottom': 'none' });
                wd.css(wd.dom.id('config_side'), { 'borderBottom': 'none' });

                sObj.setAttribute('show', show);
                dObj.setAttribute('show', show);
                wd.dom.tag('b', sObj)[0].className = bcss;
                wd.dom.tag('b', dObj)[0].className = bcss;
            };
        }

        function hideShow(n, css) {
            var conTr = wd.dom.tag('tr', wd.dom.id('tab_' + n)),
                sideTr = wd.dom.tag('tr', wd.dom.id('tab_side')),
                conLen = conTr.length,
                sideLen = sideTr.length,
                j = 1, k = 0;

            for (; j < conLen; j++)
                wd.css(conTr[j], 'display', css);

            for (; k < sideLen; k++) {
                var obj = sideTr[k];
                if (obj.getAttribute('rel') == n)
                    wd.css(obj, 'display', css);
            }
        };
    };

    //表格隔行变色
    this.mouseChangeCss = function () {
        var tr = wd.dom.tag('tr', configData),
            len = tr.length,
            i = 0;

        for (; i < len; i++) {
            wd.handler.addEvent(tr[i], 'mouseenter', function (e) {
                e = e || window.event;
                var elem = e.currentTarget || e.srcElement,
                    currColor = elem.style.backgroundColor,
                    ids = elem.id.replace('trs_', '').replace('tr_', '');
                if (currColor == 'rgb(240, 243, 248)' || currColor == '#f0f3f8') return;

                if (wd.dom.id('trs_' + ids)) {
                    wd.css(wd.dom.id('trs_' + ids), 'background', '#f8f5f0');
                    wd.css(wd.dom.id('tr_' + ids), 'background', '#f8f5f0');
                }
            });

            wd.handler.addEvent(tr[i], 'mouseleave', function (e) {
                e = e || window.event;
                var elem = e.currentTarget || e.srcElement,
                    currColor = elem.style.backgroundColor,
                    ids = elem.id.replace('trs_', '').replace('tr_', '');
                if (currColor == 'rgb(240, 243, 248)' || currColor == '#f0f3f8') return;

                if (wd.dom.id('trs_' + ids)) {
                    wd.css(wd.dom.id('trs_' + ids), 'background', '#FFFFFF');
                    wd.css(wd.dom.id('tr_' + ids), 'background', '#FFFFFF');
                }
            });

            tr[i].onclick = function () {
                var ids = this.id.replace('trs_', '').replace('tr_', ''),
                    currColor = this.style.backgroundColor;

                if (currColor == 'rgb(240, 243, 248)' || currColor == '#f0f3f8') {
                    if (wd.dom.id('trs_' + ids)) {
                        wd.css(wd.dom.id('trs_' + ids), 'backgroundColor', '#FFFFFF');
                        wd.css(wd.dom.id('tr_' + ids), 'backgroundColor', '#FFFFFF');
                        self.Data.userClickTR[ids] = '';
                    }
                }
                else {
                    if (wd.dom.id('trs_' + ids)) {
                        self.Data.userClickTR[ids] = '-' + ids + '-';
                        wd.css(wd.dom.id('trs_' + ids), 'backgroundColor', '#F0F3F8');
                        wd.css(wd.dom.id('tr_' + ids), 'backgroundColor', '#F0F3F8');
                    }
                }
            };
        }
    };

    //页面加载初始化复选框
    this.checkboxInit = function () {
        var cbox = wd.dom.tag('input'),
            len = cbox.length,
            i = 0;

        for (; i < len; i++) {
            if (cbox[i].type == 'checkbox') {
                cbox[i].checked = false;
            }
        }
    };


    //复选框事件
    this.selectEvent = function () {
        var cbox = wd.dom.tag('input'),
            len = cbox.length,
            i = 0;

        for (; i < len; i++) {

            if (cbox[i].type == 'checkbox') {
                cbox[i].onclick = function () {

                    var ids = self.selectCar().join(',');

                    switch (this.name) {
                        case 'radioShow':
                            aysnBox(this.name, this.checked, this.value);
                            if ((self.Data.allList.length == 0 || self.Data.carList.length < 2) && this.checked) return;
                            if (this.value == 'hide') {
                                self.Data.hide = this.checked;
                            }
                            else if (this.value == 'high')
                                self.Data.high = this.checked;

                            break;
                        case 'syear':
                        case 'engine':
                        case 'transmission':
                        case 'carStruct':
                            self.responseNav(ids);
                            break;
                    };

                    self.responseContent(ids);
                    self.scrollFloat(true);
                    self.selectEvent();
                    //新统计                    
                };
            }
        }


        function aysnBox(n, c, v) {
            for (var j = 0; j < len; j++) {
                if (cbox[j].type == 'checkbox' && cbox[j].name == n && cbox[j].value == v)
                    cbox[j].checked = c;
            }
        };
    };



    //筛选
    this.selectCar = function () {
        var cbox = wd.dom.tag('input'),
            len = cbox.length,
            ids = '',
            selectIndex = [],
            syear = [],
            engine = [],
            transmission = [],
            carStruct = [],
            checknum = 0;

        for (var j = 0; j < len; j++) {
            if (cbox[j].type == 'checkbox' && cbox[j].checked) {
                switch (cbox[j].name) {
                    case 'syear':
                        syear.push(cbox[j].value);
                        checknum++;
                        break;
                    case 'engine':
                        engine.push(cbox[j].value);
                        checknum++;
                        break;
                    case 'transmission':
                        transmission.push(cbox[j].value);
                        checknum++;
                        break;
                    case 'carStruct':
                        carStruct.push(cbox[j].value);
                        checknum++;
                        break;
                }
            }
        }

        if (checknum > 0) this.Data.isCheckBox = true;
        else this.Data.isCheckBox = false;

        syear = findkey('syear', syear, self.Data.syear);
        engine = findkey('engine', engine, self.Data.engine);
        transmission = findkey('transmission', transmission, self.Data.transmission);
        carStruct = findkey('carStruct', carStruct, self.Data.carStruct);
        //查找KEY

        function findkey(id, arr, cmparr) {
            var ids = [],
                cmparrLen = cmparr.length,
                arrLen = arr.length;
            for (var k = 0; k < cmparrLen; k++) {
                if (arrLen > 0) {
                    for (var j = 0; j < arrLen; j++) {
                        if (cmparr[k].indexOf(arr[j]) > -1) {
                            ids.push(k);
                        }
                    }
                } else {
                    ids.push(k);
                }
            }
            return ids;
        };



        //将内容转为数组
        function getArray(str) {
            var temp = [];
            if (str != null) {
                str = str.split(",");
                for (var i in str) temp.push(str[i]);
            }
            return temp;
        }

        //获取交集
        function getIntersect(a, b) {

            var temp = [];
            temp = (a.join(",") + "|" + b.join(",")).match(/(\b[^,]+\b)(?!.*\b\1\b.*\|)(?=.*\|.*\b\1\b)/g);
            return temp == null ? [] : temp;
        };


        selectIndex = getIntersect(syear, getIntersect(engine, getIntersect(transmission, carStruct)));

        for (var i = 0, silen = selectIndex.length; i < silen; i++) {
            selectIndex[i] = '-' + selectIndex[i] + '-';
        }

        return selectIndex;
    };

    this.MoveOrDel = function () {

        var del = function (index) {
            if (index == -1) return;
            //清空数据
            var configitems = config.result.paramtypeitems,
                
                clearData = function (arr, n, attr) {

                    for (var i = 0, len = arr.length; i < len; i++) {
                        for (var j = 0, jlen = arr[i][attr + 'items'].length; j < jlen; j++) {
                            arr[i][attr + 'items'][j].valueitems.splice(n, 1);
                        }
                    }
                };

            clearData(configitems, index, 'param');            
            
            self.Data.syear.splice(index, 1);
            self.Data.engine.splice(index, 1);
            self.Data.transmission.splice(index, 1);
            self.Data.carStruct.splice(index, 1);
            self.Data.carNum--;
        },
        getIndex = function (specid) {
            var arr = self.Data.allList,
                len = arr.length,
                s = -1;
            for (var i = 0; i < len; i++) {
                if (arr[i] == specid) {
                    s = i;
                    break;
                }
            }
            return s;
        },
        exchange = function (id, n, cmd) {
            var tds = wd.dom.tag('td', configNav),
                changeId = 0;

            switch (cmd) {
                case 'l':
                    changeId = wd.dom.byClass('btn_del', tds[n - 2], 'a')[0].getAttribute('rel');
                    break;
                case 'r':
                    changeId = wd.dom.byClass('btn_del', tds[n], 'a')[0].getAttribute('rel');
                    break;
            }

            var curIndex = getIndex(id),
                changeIndex = getIndex(changeId);

            //数据交换
            var temp = null,
                configitems = config.result.paramtypeitems,
                
                arrChangeData = function (arr) {
                    temp = arr[curIndex];
                    arr[curIndex] = arr[changeIndex];
                    arr[changeIndex] = temp;
                };

            self.changeData(configitems, curIndex, changeIndex, 'param');            


            arrChangeData(self.Data.syear);
            arrChangeData(self.Data.engine);
            arrChangeData(self.Data.transmission);
            arrChangeData(self.Data.carStruct);
        },
        delAnimate = function (n, fn) {
            var navTab = wd.dom.tag('table', configNav)[0],
                dataTab = wd.dom.tag('table', configData),
                tempId = 0,
                runNum = 0,
                conWidth = (parseInt(wd.css(wd.dom.tag('table', configNav)[0], 'width').replace(/px/gi, '')) + 1) + 'px',
                isHideDel = function (c) {
                    //先清空表格内容
                    if (c)
                        navTab.rows[0].cells[n].innerHTML = '<div id="temp_' + (tempId++) + '" style="padding:0;width:160px;" class="carbox"></div>';
                    else
                        navTab.rows[0].deleteCell(n);

                    for (var i = 0, len = dataTab.length; i < len; i++) {
                        var tr = wd.dom.tag('tr', dataTab[i]),
                            trLen = tr.length,
                            flag = true;

                        for (var j = 0; j < trLen; j++) {
                            if (tr[j].cells.length > 1) {
                                if (c) {
                                    if (flag) {
                                        tr[j].cells[n].innerHTML = '<div id="temp_' + (tempId++) + '"></div>';
                                        flag = false;
                                    }
                                    else
                                        tr[j].cells[n].innerHTML = '';
                                }
                                else
                                    tr[j].deleteCell(n);
                            }
                        }

                        wd.css(dataTab[i], { 'width': 'auto' });
                    }
                },
                runed = function () {
                    runNum++;
                    if (runNum == tempId) {
                        fn();
                    }
                };

            isHideDel(true);

            for (var i = 0; i < tempId; i++)
                wd.animate(wd.dom.id('temp_' + i)).start({ 'width': '0px' }, 250, runed);
        },
        moveAnimate = function (id, n, cmd, fn) {
            //创建浮动层
            var navTab = wd.dom.tag('table', configNav)[0],
                dataTab = wd.dom.tag('table', configData),
                td = navTab.rows[0].cells[n],
                w = wd.css(td, 'width'),
                div = wd.dom.create('div'),
                scrollXY = wd.abs.scroll(td),
                posXY = wd.abs.point(td),
                conXY = wd.abs.point(wd.dom.id('content')),
                go,
                textCss = 'background:#F0F3F8;',
                navposition = wd.css(configNav, 'position');

            wd.css(div, {
                'opacity': '0.5',
                'position': 'absolute',
                'zIndex': '1300',
                'top': conXY.y + 'px',
                'left': (navposition == 'relative' ? posXY.x : (scrollXY.left + posXY.x)) + 'px',
                'width': w,
                'background': '#F0F3F8',
                'overflow': 'hidden'
            });

            var topHtml = '<div class="operation">' +
                          '   <table cellspacing="0" cellpadding="0" class="tbset">' +
                          '       <tbody>' +
                          '           <tr style="#COLOR#">' +
                          '               <td>#TOP#</td>' +
                          '           </tr>' +
                          '       </tbody>' +
                          '    </table> ' +
                          '</div> ',
                conHtml = '<table cellspacing="0" cellpadding="0" class="tbcs" style="width:160px;">' +
                          '     <tbody>' +
                          '         <tr style="#COLOR##HEIGHT#">' +
                          '             <td ' + (id == sid ? 'style="background:' + self.Data.curSpecCss + ';"' : '') + '>#CON#</td>' +
                          '         </tr>' +
                          '     </tbody>' +
                          '</table>',
                colorHtml = '<table cellspacing="0" cellpadding="0" class="tbcs" style="width:160px;">' +
                          '     <tbody>' +
                          '         <tr style="#COLOR#height:87px;">' +
                          '             <td ' + (id == sid ? 'style="background:' + self.Data.curSpecCss + ';"' : '') + '>#CON#</td>' +
                          '         </tr>' +
                          '     </tbody>' +
                          '</table>',
                titHtml = '<table cellspacing="0" cellpadding="0" class="tbcs" style="width:160px;">' +
                          '     <tbody>' +
                          '         <tr>' +
                          '             <th class="cstitle"><h3></h3></th>' +
                          '         </tr>' +
                          '     </tbody>' +
                          '</table>',
                html = [];

            html.push('<div class="pzbox" ' + (wd.css(configNav, 'position') == 'relative' ? '' : 'style="position:relative;top:' + (scrollXY.top - conXY.y) + 'px"') + '>');
            html.push(topHtml.replace('#TOP#', td.innerHTML));
            html.push('</div>');

            html.push('<div class="pzbox">');
            html.push('<div class="conbox">');

            for (var i = 1, len = dataTab.length; i < len; i++) {
                var tr = wd.dom.tag('tr', dataTab[i]),
                    trLen = tr.length;
                if (i != 1)
                    html.push(titHtml);
                for (var j = 0; j < trLen; j++) {
                    if (tr[j].style.display == 'none') break;
                    if (tr[j].cells.length > 1) {
                        var color = tr[j].style.backgroundColor,
                            isTwo = tr[j].cells[0].className == 'twol';
                        if (i == len - 1 && j == trLen - 1) {
                            if (color == 'rgb(240, 243, 248)' || color == '#f0f3f8') {
                                html.push(colorHtml.replace('#CON#', tr[j].cells[n].innerHTML).replace('#COLOR#', textCss));
                            }
                            else {
                                html.push(colorHtml.replace('#CON#', tr[j].cells[n].innerHTML).replace('#COLOR#', ''));
                            }
                        }
                        else {
                            if (color == 'rgb(240, 243, 248)' || color == '#f0f3f8') {
                                html.push(conHtml.replace('#CON#', tr[j].cells[n].innerHTML).replace('#COLOR#', textCss).replace('#HEIGHT#', isTwo ? 'height:46px;' : 'height:29px;'));
                            }
                            else {
                                html.push(conHtml.replace('#CON#', tr[j].cells[n].innerHTML).replace('#COLOR#', '').replace('#HEIGHT#', isTwo ? 'height:46px;' : 'height:29px;'));
                            }
                        }
                    }
                }
            }

            html.push('</div>');
            html.push('</div>');

            div.innerHTML = html.join('');

            document.body.appendChild(div);

            switch (cmd) {
                case 'l':
                    go = ((navposition == 'relative' ? posXY.x : scrollXY.left + posXY.x) - parseInt(w.replace(/px/gi, ''))) + 'px';
                    break;
                case 'r':
                    go = ((navposition == 'relative' ? posXY.x : scrollXY.left + posXY.x) + parseInt(w.replace(/px/gi, ''))) + 'px';
            }

            wd.animate(div).start({ 'left': go }, 350, function () {
                document.body.removeChild(div);
                setTimeout(function () {
                    fn();
                    var ids = self.selectCar().join(',');
                    self.responseNav(ids);
                    self.responseContent(ids);
                    self.scrollFloat(true);
                    self.selectEvent();
                }, 10);
            });
        };

        configNav.onclick = function (e) {
            e = e || window.event;
            var b = e.target || e.srcElement,
                current = b,
                n;

            if (!b.className && (b.className != 'btn_del' || b.className != 'switch_left' || b.className != 'switch_right')) return;

            while (current && current.tagName != "TD") {
                current = current.parentNode;
            }

            current && (n = current.cellIndex);

            switch (b.className) {
                case 'btn_del': //删除
                    var id = b.getAttribute('rel');

                    isDelAnimate(function () {
                        del(getIndex(id));
                    });
                    break;
                case 'switch_left': //左移动
                    var id = b.getAttribute('rel');
                    if (wd.browsers.isIE || wd.browsers.chrome) {
                        ieMove(function () {
                            exchange(id, n, 'l');
                        });
                    }
                    else {
                        moveAnimate(id, n, 'l', function () {
                            exchange(id, n, 'l');
                        });
                    }
                    break;
                case 'switch_right': //右移动
                    var id = b.getAttribute('rel');
                    if (wd.browsers.isIE || wd.browsers.chrome) {
                        ieMove(function () {
                            exchange(id, n, 'r');
                        });
                    }
                    else {
                        moveAnimate(id, n, 'r', function () {
                            exchange(id, n, 'r');
                        });
                    }
                    break;
            }

            function isDelAnimate(fn) {
                if (wd.browsers.isIE || wd.browsers.chrome) {
                    fn();
                    var ids = self.selectCar().join(',');
                    self.responseNav(ids);
                    self.responseContent(ids);
                    self.scrollFloat(true);
                    self.selectEvent();
                }
                else {
                    delAnimate(n, function () {
                        fn();
                        var ids = self.selectCar().join(',');
                        self.responseNav(ids);
                        self.responseContent(ids);
                        self.scrollFloat(true);
                        self.selectEvent();
                    });
                }
            };

            function ieMove(fn) {
                fn();
                var ids = self.selectCar().join(',');
                self.responseNav(ids);
                self.responseContent(ids);
                self.scrollFloat(true);
                self.selectEvent();
            };
        };
    };

    //主数据交换
    this.changeData = function (arr, curIndex, changeIndex, attr) {
        var temp = null;
        for (var i = 0, len = arr.length; i < len; i++) {
            for (var j = 0, k = arr[i][attr + 'items'].length; j < k; j++) {
                temp = arr[i][attr + 'items'][j].valueitems[curIndex];
                arr[i][attr + 'items'][j].valueitems[curIndex] = arr[i][attr + 'items'][j].valueitems[changeIndex];
                arr[i][attr + 'items'][j].valueitems[changeIndex] = temp;
            }
        }
    };

    //车型置顶  
    this.isTop = function () {
        if (!sid || sid == '' || sid == '0') return;

        var configitems = config.result.paramtypeitems,            
            items = configitems[0].paramitems[0].valueitems,
            len = items.length,
            curIndex = 0,
            changeIndex = 0,
            i = 0;

        //查找车型对应的索引
        for (; i < len; i++) {
            if (items[i].specid == sid) {
                changeIndex = i;
                break;
            }
        }

        //数据交换
        if (curIndex != changeIndex) {
            this.changeData(configitems, curIndex, changeIndex, 'param');          
        }
    };

    //输出左侧固定导航
    this.repsonseNavScrollLeft = function () {
        var content = wd.dom.id('content'),
            div = document.createElement('div'),
            i = 0, len = this.Data.navMeto.item.length, html = [];

        div.id = 'navScrollLeft';
        div.className = 'followcon';
        div.style.display = 'none';

        if (len > 0) {

            html.push('<ul class="folul">');

            for (; i < len; i++) {
                if (i == 0) {
                    html.push('<li class="top"><a class="active" target="_self" href="javascript:void(0)">' + this.Data.navMeto.item[i].name + '</a></li>');
                }
                else if (i == len - 1) {
                    html.push('<li class="bottom"><a target="_self" href="javascript:void(0)">' + this.Data.navMeto.item[i].name + '</a></li>');
                }
                else {
                    html.push('<li><a target="_self" href="javascript:void(0)">' + this.Data.navMeto.item[i].name + '</a></li>');
                }
            }

            html.push('</ul>');

            div.innerHTML = html.join('');
        }

        content.appendChild(div);

        div = null;
    };

    //左侧滚动固定导航
    this.navScrollLeft = function () {
        var that = this, timer,
            obj = wd.dom.id('navScrollLeft'),
            obj_link = obj.getElementsByTagName('a'),
            oMain = wd.dom.id('content'),
            top = 155,
            isIE6 = wd.browsers.isIE6,
            set = function () {
                var pos = wd.abs.point(oMain),
                    bpos = wd.abs.point(wd.dom.id(that.Data.navMeto.item[0].id)),
                    sT = wd.abs.scroll(),
                    style = obj.style,
                    metoList = [];

                if (sT.top > bpos.y - top) {
                    style.cssText = 'display:block;position:' + (isIE6 ? 'absolute' : 'fixed') + ';top:' + (isIE6 ? sT.top + top : top) + 'px;left:' + (pos.x - 132 - (isIE6 ? 5 : sT.left)) + 'px';
                }
                else {
                    style.cssText = 'display:block;position:absolute;top:' + bpos.y + 'px;left:' + (pos.x - 132 - (isIE6 ? 5 : 0)) + 'px';
                }
               
                for (var i = 0, len = that.Data.navMeto.item.length; i < len; i++) {
                    metoList.push({ dom: wd.dom.id(that.Data.navMeto.item[i].id), index: i });
                }

                for (var i = 0, len = metoList.length; i < len; i++) {
                    var h = wd.abs.point(metoList[i].dom).y + wd.dom.id(metoList[i].dom.getAttribute('pid')).offsetHeight - 35 - (top + i * 27);
                    if (h > sT.top) {
                        for (var j = 0, l = obj_link.length; j < l; j++) obj_link[j].className = '';
                        obj_link[i].className = 'active';
                        break;
                    }
                }
            };

        wd.handler.addEvent(obj, 'click', function (e) {
            e = e || window.event;
            var elem = e.target || e.srcElement,
                sT = wd.abs.scroll(),
                index = 0;
            if (elem.tagName.toLowerCase() === 'a') {
                for (var i = 0, len = obj_link.length; i < len; i++) obj_link[i].className = '';
                elem.className = 'active';
                var objtxt;
                for (var key in that.Data.navMeto.item) {
                    if (that.Data.navMeto.item[key].name == elem.innerHTML) {
                        index = key;
                        objtxt = elem.innerHTML;
                        break;
                    }
                }
                window.scrollTo(sT.left, wd.abs.point(wd.dom.id(that.Data.navMeto.item[index].id)).y - top - parseInt(key) * 27);              
            }

        });

        timer = setInterval(function () {
            set();
        }, 10);

        setTimeout(function () {

            clearInterval(timer);
            wd.handler.addEvent(window, 'scroll', set);
            wd.handler.addEvent(window, 'resize', set);

        }, 1000 * 10);

    };

    this.init();

    return this;
};
