
$(function () {

    //更换验证码
    $('#kaptcha').click(function () {
        $('#kaptcha').attr('src',codeUrl+'?'+Math.floor(Math.random()*100));
    });

     var productCategoryUrl = '/productCategoryApi/getProductCatetgroyList';
     var addProductUrl = '/productApp/addProduct';
    var codeUrl ='/productApp/kaptcha';
     var shopId = $('#shopId').val();

     $.ajax({
         type:'POST',
         url:productCategoryUrl,
         data:JSON.stringify({'shopId':shopId}),
         contentType: "application/json;charset=utf-8",
         success:function (res) {
             if(res.status == 200){
                 var data = res.data;
                 var productCategoryHtml = '';
                 $.each(data, function (index, obj) {
                     var html = '<option value="' + obj.productCategoryId + '">' + obj.productCategoryName + '</option>';
                     productCategoryHtml = productCategoryHtml + html;
                 });
                 $('#productCategoryId').html(productCategoryHtml);
             }
         }
     })

    //增加
    $('#submit').click(function () {
        var productName = $("#productName").val();
        var productCategoryId =$('#productCategoryId').val();
        var priority = $('#priority').val();
        var normalPrice = $('#normalPrice').val();
        var promotionPrice = $('#promotionPrice').val();
        var point = $('#point').val();
        var productImg = $('#productImg')[0].files[0];
        var productDescImg = $('#productDescImg')[0].files[0];
        var productDesc = $('#productDesc').val();
        var code = $('#code').val();
        var form = new FormData();
        form.append('productName',productName);
        form.append('productCategoryId',productCategoryId);
        form.append('priority',priority);
        form.append('normalPrice',normalPrice);
        form.append('promotionPrice',promotionPrice);
        form.append('point',point);
        form.append('productImg',productImg);
        form.append('productDescImg',productDescImg);
        form.append('productDesc',productDesc);
        form.append('code',code);
        //上传
        $.ajax({
            type: "POST",//方法类型
            url: addProductUrl,//url
            dataType:'json',
            data:form,
            //async: false,
            contentType: false, //禁止设置请求类型
            processData: false, //禁止jquery对DAta数据的处理,默认会处理
            success: function (res) {
                if(res.status == 200){
                    $.toast("添加成功");
                }else{
                    $.toast(res.msg);
                }
            },
            error:function () {
                $.toast("服务器异常");
            }
        });
    });


})