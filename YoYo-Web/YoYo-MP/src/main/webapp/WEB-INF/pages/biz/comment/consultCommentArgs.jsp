<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
    String path = request.getContextPath();
	request.setAttribute("path", path);
	Long time=System.currentTimeMillis();
	request.setAttribute("time", time);
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="pragma" content="no-cache"> 
<meta http-equiv="cache-control" content="no-cache"> 
<meta http-equiv="expires" content="0">
<jsp:include page="${_path}/WEB-INF/pages/decorator/inc.jsp" />
<link type="text/css" href="${path}/resources/styles/comment/commentSetting.css?t=${time}" rel="stylesheet" />
<script type="text/javascript" src="${path}/resources/scripts/biz/member/consultCommentArgs.js?t=${time} "></script>
<title>咨询评论设置</title>
</head>
<body>
<div id="main" class="content-main" style="height: 569px; width:auto;">
	<div class="easyui-tabs" style="width:auto;height:auto">
		<!-- 基本设置开始 -->
		<div title="基本设置" style="padding: 10px">
			<div id="base_setting">
				<form action="../consultCommentArgs/saveConsultCommentBaseArgs" method="post">
					<table class="tableform">
						<tbody>
							<tr>
								<th><lable id="lab_indexnum">评论、咨询初始显示数量</lable>：</th>
								<td>
									<input type="text" value="${args.BaseArguments.initializeNum}" name="initializeNum" style="width: 50px" size="5" maxlength="2" class="x-input " autocomplete="off"  onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')" />
									条顾客点击“查看全部”时会展开显示所有评论或咨询
								</td>
							</tr>
							<tr>
								<th><lable id="lab_display_lv">评论、咨询显示会员等级</lable>：</th>
								<td>
									<input type="radio" value="1" <c:if test="${args.BaseArguments.displayLv==1}">checked="checked"</c:if> name="displayLv">是 
									<input type="radio" value="0" <c:if test="${args.BaseArguments.displayLv==0}">checked="checked"</c:if> name="displayLv">否
								</td>
							</tr>
							<tr>
								<th><lable id="lab_switch_reply">评论、咨询开启回复功能</lable>：</th>
								<td>
									<input type="radio" value="1" <c:if test="${args.BaseArguments.switchReply==1}">checked="checked"</c:if> name="switchReply">是
									<input type="radio" value="0" <c:if test="${args.BaseArguments.switchReply==0}">checked="checked"</c:if> name="switchReply">否
								</td>
							</tr>
							<tr>
								<th><lable id="lab_verifyCode">发表时需输入验证码</lable>：</th>
								<td>
									<input type="radio" value="1" <c:if test="${args.BaseArguments.verifycode==1}">checked="checked"</c:if> name="verifycode">是 
									<input type="radio" value="0" <c:if test="${args.BaseArguments.verifycode==0}">checked="checked"</c:if> name="verifycode">否
								</td>
							</tr>
							<tr>
								<th><lable id="lab_display">审核设置</lable>：</th>
								<td>
									<input type="radio" value="1" <c:if test="${args.BaseArguments.display==1}">checked="checked"</c:if> name="display">管理员审核后显示
									<input type="radio" value="0" <c:if test="${args.BaseArguments.display==0}">checked="checked"</c:if> name="display">用户发表后立即显示
								</td>
							</tr>
						</tbody>
					</table>
					<div class="table-action">
						<input type="hidden" value="${args.BaseArguments.id}" name="id"/>
						<button class="btn" type="submit">
							<span><span>保存</span></span>
						</button>
					</div>
				</form>
			</div>
		</div>
		<!-- 基本设置结束-->
		
		
		<!-- 咨询设置开始 -->
		<div title="咨询设置" style="padding:10px">
			<div id="consult_setting">
				<form action="../consultCommentArgs/saveConsultArgs" method="post" name="consult_form">
					<div class="tableform">
						<h4>基本设置：</h4>
						<div class="division">
							<table width="100%" cellspacing="0" cellpadding="2" border="0">
								<tbody>
									<tr>
										<th>是否开启咨询功能：</th>
										<td>
											<input type="radio" name="openDiscuss" <c:if test="${args.ConsultArguments.openDiscuss==1}">checked="checked"</c:if> value="1" id="dom_el_3dd12f4-1" />
											<label for="dom_el_3dd12f4-1">开启</label><br>
											<input type="radio" name="openDiscuss" <c:if test="${args.ConsultArguments.openDiscuss==0}">checked="checked"</c:if> value="0" id="dom_el_3dd12f4-2" />
											<label for="dom_el_3dd12f4-2">关闭</label>
										</td>
									</tr>
									<tr>
										<th>发表咨询权限：</th>
										<td>
											<input type="radio" name="sendPermission" <c:if test="${args.ConsultArguments.openDiscuss==1}">checked="checked"</c:if> value="1" id="dom_el_3dd12f5-1"/>
											<label for="dom_el_3dd12f5-1">所有顾客都可咨询</label><br>
											<input type="radio" name="sendPermission" <c:if test="${args.ConsultArguments.openDiscuss==2}">checked="checked"</c:if> value="2" id="dom_el_3dd12f5-2"/>
											<label for="dom_el_3dd12f5-2">只有注册会员才能咨询</label><br>
										</td>
									</tr>
								</tbody>
							</table>
						</div>
						<h4>提示文字设置：</h4>
						<div class="division">
							<table width="100%" cellspacing="0" cellpadding="2" border="0">
								<tbody>
									<tr>
										<th>无评论时缺省文字：</th>
										<td>
											<input type="text" value="${args.ConsultArguments.defaultNotic}" size="30" style="width: 360px" name="defaultNotic" class="x-input " autocomplete="off" maxlength="50" />
										</td>
									</tr>
									<tr>
										<th>等待审核提示:</th>
										<td>
											<input type="text" value="${args.ConsultArguments.waitCheckNotic}" size="30" name="waitCheckNotic" style="width: 360px" class="x-input " autocomplete="off" maxlength="50" />
										</td>
									</tr>
									<tr>
										<th>发表成功提示文字</th>
										<td>
											<input type="text" value="${args.ConsultArguments.sendSuceessNotic}" size="30" name="sendSuceessNotic" style="width: 360px" class="x-input " autocomplete="off" maxlength="50" />
										</td>
									</tr>
									<tr>
										<th>用户填写咨询时所见提示：</th>
										<td>
											<input type="text" value="${args.ConsultArguments.sendNotic}" style="width: 360px" size="30" name="sendNotic" class="x-input " autocomplete="off" maxlength="50" />
										</td>
									</tr>
								</tbody>
							</table>
						</div>
						<h4>高级设置：</h4>
						<div class="division">
							<table width="100%" cellspacing="0" cellpadding="2" border="0" id="consult_items">
								<tbody>
									<tr>
										<th>咨询项目：</th>
										<td></td>
									</tr>
									<c:forEach var="item" items="${args.ConsultItems}">
									<tr> 
										<th></th>
										<td>
											<input type="hidden" value="${item.id}" name="itemId" />
											<input type="text" value="${item.consultTitle}" name="title" class="x-input " autocomplete="off" maxlength="20" />
											<span class="delete_gask_type" data-id="${item.id}">
											<img class="del-tag lnk" src="${path}/resources/images/delete.gif">
											</span>
										</td>
									</tr>
									</c:forEach>						
								</tbody>
							</table>
							<table>
								<tbody>									
									<tr>
										<th>
										</th>
										<td>
											<button type="button" class="btn" id="add_gask_type">
												<span><span>添加</span></span>
											</button>
										</td>
									</tr>
								</tbody>
							</table>
						</div>
						<div class="table-action">
							<input type="hidden" value="${args.ConsultArguments.id}" name="id">
							<button class="btn" type="button" id="sub_form_2">
								<span><span>保存</span></span>
							</button>
						</div>
					</div>
				</form>
			</div>
		</div>
		<!-- 咨询设置结束 -->
		
		
		<!-- 评论设置开始 -->
		<div title="评论设置" style="padding:10px">
			<div id="comment_setting">
				<form action="../consultCommentArgs/saveCommentArgs" method="post" name="comment_form">
					<div class="tableform">
						<h4>基本设置：</h4>
						<div class="division">
							<table width="100%" cellspacing="0" cellpadding="2" border="0">
								<tbody>
									<tr>
										<th>是否开启评论功能：</th>
										<td>
											<input type="radio" <c:if test="${args.CommentComment.openDiscuss==1}">checked="checked"</c:if> value="1" id="dom_el_973dc33-1" name="openDiscuss">
											<label for="dom_el_973dc33-1">开启</label><br>
											<input type="radio" <c:if test="${args.CommentComment.openDiscuss==0}">checked="checked"</c:if> value="0" id="dom_el_973dc33-2" name="openDiscuss">
											<label for="dom_el_973dc33-2">关闭</label>
										</td>
									</tr>
									<tr>
										<th>发表评论权限：</th>
										<td>
											<input type="radio" <c:if test="${args.CommentComment.sendPermission==3}">checked="checked"</c:if> value="4" id="dom_el_973dc34-1" name="sendPermission"/>
											<label for="dom_el_973dc34-1">非会员可发表评论</label><br>
											<input type="radio" <c:if test="${args.CommentComment.sendPermission==4}">checked="checked"</c:if> value="4"  id="dom_el_973dc34-2" name="sendPermission"/>
											<label for="dom_el_973dc34-2">注册会员可发表评论</label><br>
											<input type="radio" <c:if test="${args.CommentComment.sendPermission==5}">checked="checked"</c:if> value="5" id="dom_el_973dc34-3" name="sendPermission" />
											<label for="dom_el_973dc34-3">只有购买过此商品的会员才可发表评论</label>
										</td>
									</tr>
								</tbody>
							</table>
						</div>
						<h4>提示文字设置：</h4>
						<div class="division">
							<table width="100%" cellspacing="0" cellpadding="2" border="0">
								<tbody>
									<tr>
										<th>无评论时缺省文字：</th>
										<td>
											<input type="text" value="${args.CommentComment.defaultNotic}" size="30" style="width: 360px" name="defaultNotic" class="x-input " autocomplete="off" maxlength="50"/>
										</td>
									</tr>
									<tr>
										<th>等待审核提示:</th>
										<td>
											<input type="text" value="${args.CommentComment.waitCheckNotic}" size="30" name="waitCheckNotic" style="width: 360px" class="x-input " autocomplete="off" maxlength="50"/>
										</td>
									</tr>
									<tr>
										<th>发表成功提示文字</th>
										<td>
											<input type="text" value="${args.CommentComment.sendSuceessNotic}" size="30" name="sendSuceessNotic" style="width: 360px" class="x-input " autocomplete="off" maxlength="50"/>
										</td>
									</tr>
									<tr>
										<th>用户填写评论时所见提示：</th>
										<td>
											<input type="text" value="${args.CommentComment.sendNotic}" style="width: 360px" size="30" name="sendNotic" class="x-input " autocomplete="off" maxlength="50"/>
										</td>
									</tr>
									<tr>
										<th>商品评价说明：</th>
										<td>
											<input type="text" value="${args.CommentComment.goodsDiscussNotice}" style="width: 360px" size="30" name="goodsDiscussNotice" class="x-input " autocomplete="off" maxlength="50" />
										</td>
									</tr>
									<tr>
										<th>评论审核后可获得积分：</th>
										<td><input type="text" value="${args.CommentComment.successPoint}" name="successPoint" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')"  maxlength="10"/></td>
									</tr>
								</tbody>
							</table>
						</div>
						<h4>高级设置：</h4>
						<div class="division">
							<table width="100%" cellspacing="0" cellpadding="2" border="0">
								<tbody>
									<tr>
										<th>是否开启 评分：</th>
										<td>
											<input type="radio" class="status" value="1" checked="checked" name="openPointStatus" />是
											<input type="radio" class="status" value="0" name="openPointStatus" />否
										</td>
									</tr>
									<tr>
										<th>评分项目：</th>
										<td>
											<ul>
												<c:forEach var="point" items="${args.GoodsPoints}">
												<li>
													<input type="hidden" value="${point.id}" name="id" class="x-input " autocomplete="off" />
													<input type="type" requird="评分标题不能为空" value="${point.pointTitle}" name="pointTitle" class="x-input " autocomplete="off" maxlength="20" /> 
													<input type="checkbox" <c:if test="${point.enabled==1}">checked="checked"</c:if> value="${point.enabled}" name="enabled" />是否启用
													<span class="delete_comment_type" data-id="${point.id}">
														<img class="del-tag lnk" src="${path}/resources/images/delete.gif">
													</span> 
													<div>
														<span>5分描述：</span><textarea requird="5分描述不能为空" name="oneDesc" rows="5" clos="10">${point.oneDesc}</textarea>
														<span>4分描述：</span><textarea requird="4分描述不能为空" name="fourDesc" rows="5" clos="10">${point.fourDesc}</textarea>
														<span>3分描述：</span><textarea requird="3分描述不能为空" name="threeDesc" rows="5" clos="10">${point.threeDesc}</textarea>
														<span>2分描述：</span><textarea requird="2分描述不能为空" name="towDesc" rows="5" clos="10">${point.towDesc}</textarea>
														<span>1分描述：</span><textarea requird="1分描述不能为空" name="fiveDesc" rows="5" clos="10">${point.fiveDesc}</textarea>
													</div>
												</li>
												</c:forEach>
											</ul>
										</td>
									</tr>
									<tr>
										<th></th>
										<td>
											<button type="button" class="btn" id="add_point_type" value="添加">
												<span><span>添加</span></span>
											</button>
										</td>
									</tr>
								</tbody>
							</table>
						</div>
						<div class="table-action">
							<input type="hidden" value="" name="goodsPoint" id="goods_point">
							<input type="hidden" value="${args.CommentComment.id}" name="id">
							<button class="btn" type="button" id="sub_form_3">
								<span><span>保存</span></span>
							</button>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>
<!-- 评分模版HTML -->
<ul id="point_template" style="display: none;">
	<li>
		<input type="hidden" name="id" class="x-input " autocomplete="off" />
		<input type="type" requird="评分标题不能为空" name="pointTitle" class="x-input " autocomplete="off" maxlength="20" /> 
		<input type="checkbox" value="0" name="enabled" />是否启用
		<span class="delete_comment_type" type_id="1">
		<img class="del-tag lnk" data-id="${item.id}" src="${path}/resources/images/delete.gif">
		</span>
		<div>
			<span>5分描述：</span><textarea requird="5分描述不能为空" name="oneDesc" rows="5" clos="10"></textarea>
			<span>4分描述：</span><textarea requird="4分描述不能为空" name="fourDesc" rows="5" clos="10"></textarea>
			<span>3分描述：</span><textarea requird="3分描述不能为空" name="threeDesc" rows="5" clos="10"></textarea>
			<span>2分描述：</span><textarea requird="2分描述不能为空" name="towDesc" rows="5" clos="10"></textarea>
			<span>1分描述：</span><textarea requird="1分描述不能为空" name="fiveDesc" rows="5" clos="10"></textarea>
		</div>
	</li>
</ul>
<!-- 咨询模版HTML -->
<table id="ask_item_template" style="display: none;">
	<tr>
		<th></th>
		<td>
			<input type="hidden" name="itemId" value="0"/>
			<input type="text" name="title" class="x-input " autocomplete="off" maxlength="20"/>
			<span class="delete_gask_type" type_id="1">
			<img class="del-tag lnk" data-id="" src="${path}/resources/images/delete.gif">
		</td>
	</tr>
</table>
</body>
</html>