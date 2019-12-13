$(function () {

    var getShopListUrl = '/shopApi/getShopList';
    var ownerId = $('#ownerId').val();
    //从后台获取数据
    $.ajax({
        type: "post",  // 请求方式
        url: getShopListUrl,  // 目标资源
        data: JSON.stringify({'ownerId':ownerId}),
        dataType: "json",  // 服务器响应的数据类型
        contentType: "application/json;charset=utf-8",
        success: function (data) {  //
            if(data.status == 200){
                var shopList = data.data.list;
                var contentHtml = '';
                $.each(shopList,function (index,obj) {
                    var statusRes = '状态异常';
                    if(obj.enableStatus == 0){
                        statusRes = '待审核';
                    }else if(obj.enableStatus == -1){
                        statusRes = '店铺下线';
                    }else if(obj.enableStatus == 1){
                        statusRes = '审核通过';
                    }

                    var html ='<div class="row row-shop">'+
                        '<div class="col-40">'+obj.shopName+'</div>'+
                        '<div class="col-40">'+statusRes+'</div>'+
                        '<div class="col-20">'+'<a href="javascript:void(0);" onclick="edit('+obj.shopId+')">操作</a>'+'</div>'+
                        '</div>';
                    contentHtml = contentHtml+html;
                });
                $('#shopWrap').html(contentHtml);
            }
        }
    });


})
function toAddShop(ownerId) {
    window.location.href = "/shopApi/addShopPage?ownerId=" + ownerId;
}

function edit(shopId) {
    window.location.href = "/shopApi/shopEditPage?shopId=" + shopId;
}