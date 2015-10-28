<%@ page contentType="text/html; charset=utf-8"%>

<div class="buy_t">
	<div class="b_img fl"><a href="javascript:;"><img src="${path}/resources/images/index/my_yoyo.jpg" alt=""></a></div>
    <div class="b_name fr">
    	<div class="b_name_t">
        	<div class="b_name_l fl">
            	<h2>${sessionScope.loginName}</h2>
                <p>普通会员</p>
            </div>
            <div class="b_name_r fr">
            	<span style=" margin-right: 30px;"><a href="javascript:;">我的收货地址</a></span>
                <span><a href="javascript:;">我的优惠信息(2)</a></span>
            </div>
        </div>
        <div class="b_name_b">
        	<dl>
            	<dt>账户安全：</dt>
                <dd class="buy_dd"> 
                    <i></i>
                </dd>
                <dd class="s_green">较高</dd>
            	<dt>您目前的账号存在很大的安全隐患，请及时更改密码，</dt>
                <dd><a href="${path}/accountsecurity/getAccountSecurityPage">进入账号安全管理</a></dd>
            </dl>
        </div>
	</div>
</div>

    
    