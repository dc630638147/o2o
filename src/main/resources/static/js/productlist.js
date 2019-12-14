
$(function(){
    var getProductListUrl = '/productApp/getProduct'
    var shopId = $('#shopId').val();
    $.ajax({
        type:'POST',
        url:getProductListUrl,
        data:JSON.stringify({'shopId':shopId}),
        contentType: "application/json;charset=utf-8",
        success:function (res) {
            if(res.status == 200){
                var conHtml = '';
                $.each(res.data,function (index,obj) {
                    var html = '<div class="row">'+
                        '<div class="col-40 ">'+obj.productName+'</div>'+
                        '<div class="col-20">'+obj.priority+'</div>'+
                        '<div class="col-40">'+'<a href="javascript:void(0);">编辑</a>&nbsp;'+
                          '<a href="javascript:void(0);" onclick="soldOut('+obj.productId+')">下架&nbsp;</a>'+
                        '<a href="javascript:void(0);" onclick="soldOut('+obj.productId+')">预览</a>'+
                        '</div>'+
                        '</div>';
                    conHtml = conHtml+html;
                })
                $("#productWrap").html(conHtml);
            }
        }

    })

    //下架
    $('#soldOut').click(function () {
        $.ajax()
    });

    //预览
    $('#preview').click(function () {

    })

})

function addProduct(shopId) {
    window.location.href = "/productApp/productAddPage?shopId=" + shopId;
}
function soldOut(productId) {
    $.ajax({
        type:'get',
        url:'/productApp/deleteProduct/'+productId,
        success:function (res) {
            if(res.status==200){
                $.toast("操作成功");
                window.location.reload();
            }
        }
    })
}

function preview(productId) {

}