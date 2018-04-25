/**
 * Created by Administrator on 2015/10/15.
 */
// 邮政编码验证
jQuery.validator.addMethod("isZipCode", function(value, element) {
    var tel = /^[0-9]{6}$/;
    return this.optional(element) || (tel.test(value));
}, "请正确填写邮政编码");
// 手机号验证
jQuery.validator.addMethod("mobile", function(value, element) {
    var tel = /^[0-9]{11}$/;
    return this.optional(element) || (tel.test(value));
}, "请正确填写手机号码");
// 电话号码验证
jQuery.validator.addMethod("tel", function(value, element) {
    var tel = /^[0-9]{11}$|^([0-9]{3,4}-[0-9]{7,8})$/;
    return this.optional(element) || (tel.test(value));
}, "请正确填写电话号码");
// 身份证号验证
jQuery.validator.addMethod("idCardNo", function(value, element) {
    // 身份证号码为15位或者18位，15位时全为数字，18位前17位为数字，最后一位是校验位，可能为数字或字符X
    var idCardNo =  /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;
    return this.optional(element) || (idCardNo.test(value));
}, "请正确填写身份证号");
// 日期验证 20100101格式
jQuery.validator.addMethod("dateNEW", function(value, element) {
    var idCardNo =  /^(19|20)\d\d(0[1-9]|1[012])(0[1-9]|[12]\d|3[01])$/;
    return this.optional(element) || (idCardNo.test(value));
}, "请输入有效的日期 (YYYYMMDD)");
// 当前日期 20100101格式便于设置默认值
jQuery.validator.addMethod("dateNOW", function(value, element) {
    var idCardNo =  /^(19|20)\d\d(0[1-9]|1[012])(0[1-9]|[12]\d|3[01])$/;
    return this.optional(element) || (idCardNo.test(value));
}, "请输入有效的日期 (如20150101)");
// 日期验证 20100101格式
jQuery.validator.addMethod("year", function(value, element) {
    var idCardNo =  /^(19|20)\d\d$/;
    return this.optional(element) || (idCardNo.test(value));
}, "请输入有效的年份 (YYYY)");

jQuery.validator.addMethod("numberAndLetter", function(value, element) {
    var idCardNo =  /^[A-Za-z0-9]+$/;
    return this.optional(element) || (idCardNo.test(value));
}, "请输入数字和字母");


// 覆盖默认选项，修改提示样式
jQuery.validator.setDefaults({
    errorElement: "em",
    errorPlacement: function ( error, element ) {
        // Add the `help-block` class to the error element
        error.addClass( "help-block" );

        if ( element.prop( "type" ) === "checkbox" ||element.prop( "type" ) === "radio") {
            // error.insertAfter( element.parent().parent() );
            error.appendTo(element.parent().parent());
        } else if (element.hasClass("datepicker")) {
            // datepicker 提示需要设置在上上层
            // error.insertAfter( element.parent() );
            error.appendTo(element.parent().parent() );
        } else {
            // error.insertAfter( element );
            if (element.parent().hasClass("input-group")) {
                error.appendTo(element.parent().parent() );
            } else {
                error.appendTo(element.parent() );
            }
        }
    },
    highlight: function( element, errorClass, validClass ) {
        $( element ).parent().addClass( "has-error" ).removeClass( "has-success" );
    },
    unhighlight: function( element, errorClass, validClass ) {
        $( element ).parent().addClass( "has-success" ).removeClass( "has-error" );
    }
});