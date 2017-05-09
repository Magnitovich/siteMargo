$(document).ready(function() {

    function showSelectedElement(arr){
        $('.color').hide();
        arr.forEach(function(e){
            $("."+e.toString()).show();
        });
    }
    var firstValue = 0;
    var secondValue = 0;
    var arrayPrice = [];
    var arrayMax = [];
    var maxPrice;
    var minPrice;

    $("div[class='searchColor'] input").change(function () {
        firstValue =  parseInt($('#filterPriceFirst').text());
        secondValue = parseInt($("#filterPriceSecond").text());
        //secondValue = $("[class=filterPriceSecond]").val();
        //console.log("firstValue "+firstValue+"secondValue "+secondValue);
        arrayMax = $("[class^=priceSSS]");
        for (i=0; i<arrayMax.length; i++){
            arrayPrice[i] =arrayMax[i].innerHTML;
        }
        maxPrice = Math.max.apply(null, arrayPrice);
        minPrice =  Math.min.apply(null, arrayPrice);

        //console.log("Max price: "+maxPrice+" Min price: "+minPrice);

        if($("#filterColor input:checked").length == 0 && $("#filterPrice input:checked").length == 0 &&
            $("#searchPaint input:checked").length == 0 && $("#filterHeight input:checked").length == 0
            && $("#filterStructure input:checked").length == 0 && $("#filterSewed input:checked").length == 0){
            $('.color').show();
        }else{
            var price = [];
            var height = [];
            var structure = [];
            var color = [];
            var paint = [];
            var sewed = [];

            if ($("#filterSewed input:checked").length > 0){
                $("#filterSewed input:checked").each(function(){
                    sewed.push($(this).val());
                })
            }
            if($("#filterStructure input:checked").length > 0){
                $("#filterStructure input:checked").each(function(){
                    //удаляем 1 элемент, splice служит для добавления/удаления элем из массива
                    //in this code we remove 1 elements. Splice(obj,1)
                    var k = $(this).val();
                    structure.push(k);
                })
            }
            //console.log("structure: "+structure);

            if($("#filterColor input:checked").length > 0){
                $("#filterColor input:checked").each(function(){
                    color.push($(this).attr('value'));
                })
            }
            //console.log("color: "+color);

            if($("#filterHeight input:checked").length > 0){
                $("#filterHeight input:checked").each(function(){
                    height.push($(this).attr('value'));
                })
            }
            if($("#searchPaint input:checked").length > 0){
                $("#searchPaint input:checked").each(function(){
                    paint.push($(this).attr('value'));
                })
            }
            //console.log("paint: "+paint);
            if($("#filterPrice input:checked").length > 0) {
                var arr = [];
                $("#filterPrice input:checked").each(function () {
                    var s = $(this).attr('value'); //the same $(this).val();
                    for (i = 0; i < arrayMax.length; i++) {
                        if (s == "1") {
                            if (arrayPrice[i] > 0 && arrayPrice[i] <= firstValue) {
                                //concat - copy value from selected array, now we copy in arr all info from arrayPrice[i]
                                //which to be in the selected range
                                arr = arr.concat((arrayPrice[i]));
                            }
                        } else if (s == "2") {
                            if (arrayPrice[i] > firstValue && arrayPrice[i] <= secondValue) {
                                arr = arr.concat(arrayPrice[i]);
                            }
                        } else {
                            if (arrayPrice[i] > secondValue) {
                                arr = arr.concat(arrayPrice[i]);
                            }
                        }
                    }
                });
                if (arr.length == 0) {
                    $('.color').hide();
                } else {
                    arr.forEach(function (ele) {
                        //"Привет, мир".indexOf("Привет")    // вернет 0
                        //"Привет, мир".indexOf("Корова")    // вернет -1
                        //"Привет, мир".indexOf("мир")    // вернет 8
                        if (price.indexOf(ele) == -1)
                            price.push(ele);
                    });
                }
            }
            //console.log("number: "+price);

            if(color.length == 0 && paint.length == 0 && price.length > 0 && height.length == 0
                && structure.length == 0 && sewed.length == 0){
                $('.color').hide();
                price.forEach(function(e){

//https://learn.jquery.com/using-jquery-core/faq/how-do-i-select-an-element-by-an-id-that-has-characters-used-in-css-notation/
                    $("."+e.replace( /(:|\.|\[|\]|,|=|@)/g, "\\$1" )).show();
                });
            }else if(color.length == 0 && paint.length == 0 && price.length == 0 && height.length == 0
                && structure.length ==0 && sewed.length > 0){
                $('.color').hide();

                sewed.forEach(function(e){
                    $("."+e.replace(/\s/g, '.').toString()).show();
                })
            }else if(color.length == 0 && paint.length > 0 && price.length == 0 && height.length == 0
                && structure.length ==0){

                showSelectedElement(paint)
            }else if(color.length == 0 && paint.length == 0 && price.length == 0 && height.length == 0
                && structure.length >0 && sewed.length == 0){

                showSelectedElement(structure)
            }else if(color.length == 0 && paint.length == 0 && price.length == 0 && height.length > 0
                && structure.length ==0 && sewed.length == 0){
                $('.color').hide();
                height.forEach(function(e){
                    //console.log("if height.length>0" +e);
                    $("."+e.replace( /(:|\.|\[|\]|,|=|@)/g, "\\$1" )).show();
                });
            }else if(color.length > 0 && paint.length == 0 && price.length == 0 && height.length == 0
                && structure.length ==0 && sewed.length == 0){
                showSelectedElement(color)

            }else if(color.length > 0 && paint.length > 0 && price.length == 0 && height.length == 0
                && structure.length ==0 && sewed.length == 0){
                var temp = [];
                color.forEach(function(oe){
                    paint.forEach(function(ie){
                        temp.push(oe +"."+ie);
                    });
                });
                showSelectedElement(temp);
            }
            //*************************SEWED START*******************************
            else if(color.length > 0 && paint.length == 0 && price.length == 0 && height.length == 0
                && structure.length ==0 && sewed.length > 0){
                var temp = [];
                color.forEach(function(oe){
                    sewed.forEach(function(ie){
                        temp.push(oe +"."+ie.replace(/\s/g, '.').toString());
                    });
                });
                showSelectedElement(temp);
            }
            else if(color.length == 0 && paint.length > 0 && price.length == 0 && height.length == 0
                && structure.length ==0 && sewed.length > 0){
                var temp = [];
                paint.forEach(function(oe){
                    sewed.forEach(function(ie){
                        temp.push(oe +"."+ie.replace(/\s/g, '.').toString());
                    });
                });
                showSelectedElement(temp);
            }
            else if(color.length == 0 && paint.length == 0 && price.length == 0 && height.length == 0
                && structure.length > 0 && sewed.length > 0){
                var temp = [];
                structure.forEach(function(oe){
                    sewed.forEach(function(ie){
                        temp.push(oe +"."+ie.replace(/\s/g, '.').toString());
                    });
                });
                showSelectedElement(temp);
            }
            else if(color.length == 0 && paint.length == 0 && price.length > 0 && height.length == 0
                && structure.length ==0 && sewed.length > 0){
                var temp = [];
                price.forEach(function(oe){
                    sewed.forEach(function(ie){
                        temp.push(oe.replace( /(:|\.|\[|\]|,|=|@)/g, "\\$1" ) +"."+ie.replace(/\s/g, '.').toString());
                    });
                });
                showSelectedElement(temp);
            }
            else if(color.length == 0 && paint.length == 0 && price.length == 0 && height.length > 0
                && structure.length ==0 && sewed.length > 0){
                var temp = [];
                height.forEach(function(oe){
                    sewed.forEach(function(ie){
                        temp.push(oe.replace( /(:|\.|\[|\]|,|=|@)/g, "\\$1" ) +"."+ie.replace(/\s/g, '.').toString());
                    });
                });
                showSelectedElement(temp);
            }
            //*************************SEWED FINISH*******************************
            else if(color.length > 0 && paint.length == 0 && price.length == 0 && height.length == 0
                && structure.length >0 && sewed.length == 0){
                var temp = [];
                color.forEach(function(oe){
                    structure.forEach(function(ie){
                        temp.push(oe +"."+ie);
                    });
                });
                showSelectedElement(temp);
            }else if(color.length == 0 && paint.length > 0 && price.length == 0 && height.length == 0
                && structure.length >0 && sewed.length == 0){
                var temp = [];
                paint.forEach(function(oe){
                    structure.forEach(function(ie){
                        temp.push(oe +"."+ie);
                    });
                });
                showSelectedElement(temp);
            }else if(color.length == 0 && paint.length == 0 && price.length > 0 && height.length == 0
                && structure.length >0 && sewed.length == 0){
                var temp = [];
                price.forEach(function(oe){
                    structure.forEach(function(ie){
                        temp.push(oe.replace( /(:|\.|\[|\]|,|=|@)/g, "\\$1" ) +"."+ie);
                    });
                });
                showSelectedElement(temp);
            }else if(color.length == 0 && paint.length == 0 && price.length == 0 && height.length > 0
                && structure.length >0 && sewed.length == 0){
                var temp = [];
                paint.forEach(function(oe){
                    structure.forEach(function(ie){
                        temp.push(oe.replace( /(:|\.|\[|\]|,|=|@)/g, "\\$1" ) +"."+ie);
                    });
                });
                showSelectedElement(temp);
            }else if(color.length == 0 && paint.length > 0 && price.length > 0 && height.length == 0
                && structure.length ==0 && sewed.length == 0){
                var temp = [];
                paint.forEach(function(oe){
                    price.forEach(function(ie){
                        temp.push(oe +"."+ie.replace( /(:|\.|\[|\]|,|=|@)/g, "\\$1" ));
                    });
                });
                showSelectedElement(temp);
            }else if(color.length == 0 && paint.length > 0 && price.length == 0 && height.length > 0
                && structure.length ==0 && sewed.length == 0){
                var temp = [];
                paint.forEach(function(oe){
                    height.forEach(function(ie){
                        temp.push(oe +"."+ie.replace( /(:|\.|\[|\]|,|=|@)/g, "\\$1" ));
                    });
                });
                showSelectedElement(temp);
            }else if(color.length > 0 && paint.length == 0 && price.length == 0 && height.length >0
                && structure.length ==0 && sewed.length == 0){
                var temp = [];
                color.forEach(function(oe){
                    height.forEach(function(ie){
                        temp.push(oe +"."+ie.replace( /(:|\.|\[|\]|,|=|@)/g, "\\$1" ));
                    });
                });
                showSelectedElement(temp);
            }else if(color.length == 0 && paint.length == 0 && price.length > 0 && height.length >0
                && structure.length ==0 && sewed.length == 0){
                var temp = [];
                price.forEach(function(oe){
                    height.forEach(function(ie){
                        temp.push(oe.replace( /(:|\.|\[|\]|,|=|@)/g, "\\$1" ) +"."+ie.replace( /(:|\.|\[|\]|,|=|@)/g, "\\$1" ));
                    });
                });
                showSelectedElement(temp);
            }else if(color.length > 0 && paint.length == 0 && price.length > 0 && height.length == 0
                && structure.length ==0 && sewed.length == 0){
                var temp = [];
                color.forEach(function(oe){
                    price.forEach(function(ie){
                        temp.push(oe +"."+ie.replace( /(:|\.|\[|\]|,|=|@)/g, "\\$1" ));
                    });
                });
                showSelectedElement(temp);
            }else if(color.length > 0 && paint.length > 0 && price.length > 0 && height.length == 0
                && structure.length ==0 && sewed.length == 0){
                var temp = [];
                color.forEach(function(oe){
                    paint.forEach(function(ie){
                        price.forEach(function (iie) {
                            temp.push(oe + "." + ie+"." + iie.replace(/(:|\.|\[|\]|,|=|@)/g, "\\$1"));
                        });
                    });
                });
                showSelectedElement(temp);
            }
            //*****************************SEWED START > > > **********************************
            else if(color.length > 0 && paint.length == 0 && price.length > 0 && height.length == 0
                && structure.length ==0 && sewed.length > 0){
                var temp = [];
                color.forEach(function(oe){
                    price.forEach(function(ie){
                        sewed.forEach(function (iie) {
                            temp.push(oe +"." + ie.replace(/(:|\.|\[|\]|,|=|@)/g, "\\$1")+ "." + iie.replace(/\s/g, '.').toString());
                        });
                    });
                });
                showSelectedElement(temp);
            }else if(color.length > 0 && paint.length == 0 && price.length == 0 && height.length == 0
                && structure.length > 0 && sewed.length > 0){
                var temp = [];
                color.forEach(function(oe){
                    structure.forEach(function(ie){
                        sewed.forEach(function (iie) {
                            temp.push(oe +"." + ie+ "." + iie.replace(/\s/g, '.').toString());
                        });
                    });
                });
                showSelectedElement(temp);
            }else if(color.length == 0 && paint.length == 0 && price.length > 0 && height.length == 0
                && structure.length > 0 && sewed.length > 0){
                var temp = [];
                price.forEach(function(oe){
                    structure.forEach(function(ie){
                        sewed.forEach(function (iie) {
                            temp.push(oe.replace(/(:|\.|\[|\]|,|=|@)/g, "\\$1") +"." + ie+ "." + iie.replace(/\s/g, '.').toString());
                        });
                    });
                });
                showSelectedElement(temp);
            }else if(color.length == 0 && paint.length > 0 && price.length > 0 && height.length == 0
                && structure.length ==0 && sewed.length > 0){
                var temp = [];
                paint.forEach(function(oe){
                    price.forEach(function(ie){
                        sewed.forEach(function (iie) {
                            temp.push(oe +"." + ie.replace(/(:|\.|\[|\]|,|=|@)/g, "\\$1")+ "." + iie.replace(/\s/g, '.').toString());
                        });
                    });
                });
                showSelectedElement(temp);
            }else if(color.length == 0 && paint.length == 0 && price.length > 0 && height.length > 0
                && structure.length ==0 && sewed.length > 0){
                var temp = [];
                price.forEach(function(oe){
                    height.forEach(function(ie){
                        sewed.forEach(function (iie) {
                            temp.push(oe.replace(/(:|\.|\[|\]|,|=|@)/g, "\\$1") +
                                "." + ie.replace(/(:|\.|\[|\]|,|=|@)/g, "\\$1")+ "." + iie.replace(/\s/g, '.').toString());
                        });
                    });
                });
                showSelectedElement(temp);
            }else if(color.length == 0 && paint.length == 0 && price.length == 0 && height.length > 0
                && structure.length > 0 && sewed.length > 0) {
                var temp = [];
                height.forEach(function (oe) {
                    structure.forEach(function (ie) {
                        sewed.forEach(function (iie) {
                            temp.push(oe.replace(/(:|\.|\[|\]|,|=|@)/g, "\\$1") + "." + ie + "." + iie.replace(/\s/g, '.').toString());
                        });
                    });
                });
                showSelectedElement(temp);
            }else if(color.length == 0 && paint.length > 0 && price.length == 0 && height.length == 0
                && structure.length > 0 && sewed.length > 0) {
                var temp = [];
                paint.forEach(function (oe) {
                    structure.forEach(function (ie) {
                        sewed.forEach(function (iie) {
                            temp.push(oe + "." + ie + "." + iie.replace(/\s/g, '.').toString());
                        });
                    });
                });
                showSelectedElement(temp);
            }
            //************************** FINISH SEWED > > >*************************************
            else if(color.length > 0 && paint.length > 0 && price.length == 0 && height.length == 0
                && structure.length >0 && sewed.length == 0){
                var temp = [];
                color.forEach(function(oe){
                    paint.forEach(function(ie){
                        structure.forEach(function (iie) {
                            temp.push(oe + "." + ie+"." + iie);
                        });
                    });
                });
                showSelectedElement(temp);
            }else if(color.length == 0 && paint.length > 0 && price.length > 0 && height.length == 0
                && structure.length >0 && sewed.length == 0){
                var temp = [];
                paint.forEach(function(oe){
                    price.forEach(function(ie){
                        structure.forEach(function (iie) {
                            temp.push(oe +"." + ie.replace(/(:|\.|\[|\]|,|=|@)/g, "\\$1")+ "." + iie);
                        });
                    });
                });
                showSelectedElement(temp);
            }else if(color.length == 0 && paint.length > 0 && price.length == 0 && height.length > 0
                && structure.length >0 && sewed.length == 0){
                var temp = [];
                paint.forEach(function(oe){
                    height.forEach(function(ie){
                        structure.forEach(function (iie) {
                            temp.push(oe +"." + ie.replace(/(:|\.|\[|\]|,|=|@)/g, "\\$1")+ "." + iie);
                        });
                    });
                });
                showSelectedElement(temp);
            }else if(color.length > 0 && paint.length == 0 && price.length > 0 && height.length == 0
                && structure.length >0 && sewed.length == 0){
                var temp = [];
                color.forEach(function(oe){
                    price.forEach(function(ie){
                        structure.forEach(function (iie) {
                            temp.push(oe +"." + ie.replace(/(:|\.|\[|\]|,|=|@)/g, "\\$1")+ "." + iie);
                        });
                    });
                });
                showSelectedElement(temp);
            }else if(color.length == 0 && paint.length == 0 && price.length > 0 && height.length > 0
                && structure.length >0 && sewed.length == 0){
                var temp = [];
                price.forEach(function(oe){
                    height.forEach(function(ie){
                        structure.forEach(function (iie) {
                            temp.push(oe.replace(/(:|\.|\[|\]|,|=|@)/g, "\\$1") +
                                "." + ie.replace(/(:|\.|\[|\]|,|=|@)/g, "\\$1")+"." + iie);
                        });
                    });
                });
                showSelectedElement(temp);
            }else if(color.length > 0 && paint.length > 0 && price.length == 0 && height.length > 0
                && structure.length == 0 && sewed.length == 0){
                var temp = [];
                color.forEach(function(oe){
                    paint.forEach(function(ie){
                        height.forEach(function (iie) {
                            temp.push(oe + "." + ie+"." + iie.replace(/(:|\.|\[|\]|,|=|@)/g, "\\$1"));
                        });
                    });
                });
                showSelectedElement(temp);
            }else if(color.length == 0 && paint.length > 0 && price.length > 0 && height.length > 0
                && structure.length == 0 && sewed.length == 0){
                var temp = [];
                paint.forEach(function(oe){
                    price.forEach(function(ie){
                        height.forEach(function (iie) {
                            temp.push(oe + "." + ie.replace(/(:|\.|\[|\]|,|=|@)/g, "\\$1")+
                                "." + iie.replace(/(:|\.|\[|\]|,|=|@)/g, "\\$1"));
                        });
                    });
                });
                showSelectedElement(temp);
            }else if(color.length > 0 && paint.length == 0 && price.length > 0 && height.length > 0
                && structure.length == 0 && sewed.length == 0){
                var temp = [];
                color.forEach(function(oe){
                    price.forEach(function(ie){
                        height.forEach(function (iie) {
                            temp.push(oe + "." + ie.replace(/(:|\.|\[|\]|,|=|@)/g, "\\$1")+
                                "." + iie.replace(/(:|\.|\[|\]|,|=|@)/g, "\\$1"));
                        });
                    });
                });
                showSelectedElement(temp);
            }else if(color.length > 0 && paint.length > 0 && price.length > 0 && height.length > 0
                && structure.length == 0 && sewed.length == 0){
                var temp = [];
                color.forEach(function(oe){
                    paint.forEach(function(ie){
                        price.forEach(function(iie){
                            height.forEach(function(oie){
                                temp.push(oe +"."+ie + "."+iie.replace( /(:|\.|\[|\]|,|=|@)/g, "\\$1")+"."
                                    + oie.replace( /(:|\.|\[|\]|,|=|@)/g, "\\$1" ));
                            });
                        });
                    })
                });
                showSelectedElement(temp);
            }
            //**************************START SEWED > > > > *************************
            else if(color.length > 0 && paint.length > 0 && price.length > 0 && height.length == 0
                && structure.length == 0 && sewed.length > 0){
                var temp = [];
                color.forEach(function(oe){
                    paint.forEach(function(ie){
                        price.forEach(function(iie){
                            sewed.forEach(function(oie){
                                temp.push(oe +"."+ie+
                                    "."+iie.replace( /(:|\.|\[|\]|,|=|@)/g, "\\$1")+"." + oie.replace(/\s/g, '.').toString());
                            });
                        });
                    })
                });
                showSelectedElement(temp);
            }else if(color.length > 0 && paint.length > 0 && price.length == 0 && height.length > 0
                && structure.length == 0 && sewed.length > 0){
                var temp = [];
                color.forEach(function(oe){
                    paint.forEach(function(ie){
                        height.forEach(function(iie){
                            sewed.forEach(function(oie){
                                temp.push(oe +"."+ie+
                                    "."+iie.replace( /(:|\.|\[|\]|,|=|@)/g, "\\$1")+"." + oie.replace(/\s/g, '.').toString());
                            });
                        });
                    })
                });
                showSelectedElement(temp);
            }else if(color.length > 0 && paint.length > 0 && price.length == 0 && height.length == 0
                && structure.length > 0 && sewed.length > 0){
                var temp = [];
                color.forEach(function(oe){
                    paint.forEach(function(ie){
                        structure.forEach(function(iie){
                            sewed.forEach(function(oie){
                                temp.push(oe +"."+ie+"."+iie+"." + oie.replace(/\s/g, '.').toString());
                            });
                        });
                    })
                });
                showSelectedElement(temp);
            }else if(color.length > 0 && paint.length == 0 && price.length > 0 && height.length > 0
                && structure.length == 0 && sewed.length > 0){
                var temp = [];
                color.forEach(function(oe){
                    price.forEach(function(ie){
                        height.forEach(function(iie){
                            sewed.forEach(function(oie){
                                temp.push(oe +"."+ie.replace( /(:|\.|\[|\]|,|=|@)/g, "\\$1" ) +
                                    "."+iie.replace( /(:|\.|\[|\]|,|=|@)/g, "\\$1")+"." + oie.replace(/\s/g, '.').toString());
                            });
                        });
                    })
                });
                showSelectedElement(temp);
            }else if(color.length > 0 && paint.length == 0 && price.length == 0 && height.length > 0
                && structure.length > 0 && sewed.length > 0){
                var temp = [];
                color.forEach(function(oe){
                    height.forEach(function(ie){
                        structure.forEach(function(iie){
                            sewed.forEach(function(oie){
                                temp.push(oe +"."+ie.replace( /(:|\.|\[|\]|,|=|@)/g, "\\$1" ) +
                                    "."+iie+"." + oie.replace(/\s/g, '.').toString());
                            });
                        });
                    })
                });
                showSelectedElement(temp);
            }else if(color.length > 0 && paint.length == 0 && price.length > 0 && height.length == 0
                && structure.length > 0 && sewed.length > 0){
                var temp = [];
                color.forEach(function(oe){
                    price.forEach(function(ie){
                        structure.forEach(function(iie){
                            sewed.forEach(function(oie){
                                temp.push(oe +"."+ie.replace( /(:|\.|\[|\]|,|=|@)/g, "\\$1" ) +
                                    "."+iie+"." + oie.replace(/\s/g, '.').toString());
                            });
                        });
                    })
                });
                showSelectedElement(temp);
            }else if(color.length == 0 && paint.length > 0 && price.length > 0 && height.length > 0
                && structure.length == 0 && sewed.length > 0){
                var temp = [];
                paint.forEach(function(oe){
                    price.forEach(function(ie){
                        height.forEach(function(iie){
                            sewed.forEach(function(oie){
                                temp.push(oe +"."+ie.replace( /(:|\.|\[|\]|,|=|@)/g, "\\$1" ) +
                                    "."+iie.replace( /(:|\.|\[|\]|,|=|@)/g, "\\$1")+"." + oie.replace(/\s/g, '.').toString());
                            });
                        });
                    })
                });
                showSelectedElement(temp);
            }else if(color.length == 0 && paint.length > 0 && price.length > 0 && height.length == 0
                && structure.length > 0 && sewed.length > 0){
                var temp = [];
                paint.forEach(function(oe){
                    price.forEach(function(ie){
                        structure.forEach(function(iie){
                            sewed.forEach(function(oie){
                                temp.push(oe +"."+ie.replace( /(:|\.|\[|\]|,|=|@)/g, "\\$1" ) +
                                    "."+iie+"." + oie.replace(/\s/g, '.').toString());
                            });
                        });
                    })
                });
                showSelectedElement(temp);
            }else if(color.length == 0 && paint.length > 0 && price.length == 0 && height.length > 0
                && structure.length > 0 && sewed.length > 0){
                var temp = [];
                paint.forEach(function(oe){
                    height.forEach(function(ie){
                        structure.forEach(function(iie){
                            sewed.forEach(function(oie){
                                temp.push(oe +"."+ie.replace( /(:|\.|\[|\]|,|=|@)/g, "\\$1" ) +
                                    "."+iie+"." + oie.replace(/\s/g, '.').toString());
                            });
                        });
                    })
                });
                showSelectedElement(temp);
            }else if(color.length == 0 && paint.length == 0 && price.length > 0 && height.length > 0
                && structure.length > 0 && sewed.length > 0){
                var temp = [];
                price.forEach(function(oe){
                    height.forEach(function(ie){
                        structure.forEach(function(iie){
                            sewed.forEach(function(oie){
                                temp.push(oe.replace( /(:|\.|\[|\]|,|=|@)/g, "\\$1") +"."+ie.replace( /(:|\.|\[|\]|,|=|@)/g, "\\$1" ) +
                                    "."+iie+"." + oie.replace(/\s/g, '.').toString());
                            });
                        });
                    })
                });
                showSelectedElement(temp);
            }
            //***********************Sewed > > > > FINISHED************************

            else if(color.length > 0 && paint.length == 0 && price.length > 0 && height.length > 0
                && structure.length > 0 && sewed.length == 0){
                var temp = [];
                color.forEach(function(oe){
                    price.forEach(function(ie){
                        height.forEach(function(iie){
                            structure.forEach(function(oie){
                                temp.push(oe +"."+ie.replace( /(:|\.|\[|\]|,|=|@)/g, "\\$1" ) +
                                    "."+iie.replace( /(:|\.|\[|\]|,|=|@)/g, "\\$1")+"." + oie);
                            });
                        });
                    })
                });
                showSelectedElement(temp);
            } else if(color.length > 0 && paint.length > 0 && price.length == 0 && height.length > 0
                && structure.length > 0  && sewed.length == 0){
                var temp = [];
                color.forEach(function(oe){
                    paint.forEach(function(ie){
                        height.forEach(function(iie){
                            structure.forEach(function(oie){
                                temp.push(oe +"."+ie + "."+iie.replace( /(:|\.|\[|\]|,|=|@)/g, "\\$1")+"." + oie);
                            });
                        });
                    })
                });
                showSelectedElement(temp);
            }else if(color.length > 0 && paint.length > 0 && price.length > 0 && height.length == 0
                && structure.length > 0 && sewed.length == 0){
                var temp = [];
                color.forEach(function(oe){
                    paint.forEach(function(ie){
                        price.forEach(function(iie){
                            structure.forEach(function(oie){
                                temp.push(oe +"."+ie + "."+iie.replace( /(:|\.|\[|\]|,|=|@)/g, "\\$1")+"." + oie);
                            });
                        });
                    })
                });
                showSelectedElement(temp);
            }else if(color.length == 0 && paint.length > 0 && price.length > 0 && height.length > 0
                && structure.length > 0 && sewed.length == 0){
                var temp = [];
                paint.forEach(function(oe){
                    price.forEach(function(ie){
                        height.forEach(function(iie){
                            structure.forEach(function(oie){
                                temp.push(oe +"."+ie.replace( /(:|\.|\[|\]|,|=|@)/g, "\\$1" )
                                    + "."+iie.replace( /(:|\.|\[|\]|,|=|@)/g, "\\$1")+"." + oie);
                            });
                        });
                    })
                });
                showSelectedElement(temp);
            }else if(color.length > 0 && paint.length > 0 && price.length > 0 && height.length > 0
                && structure.length == 0 && sewed.length == 0){
                var temp = [];
                color.forEach(function(oe){
                    paint.forEach(function(ie){
                        price.forEach(function(iie){
                            height.forEach(function(oie){
                                temp.push(oe +"."+ie + "."+iie.replace( /(:|\.|\[|\]|,|=|@)/g, "\\$1")+"."
                                    + oie.replace( /(:|\.|\[|\]|,|=|@)/g, "\\$1" ));
                            });
                        });
                    })
                });
                showSelectedElement(temp);
            }else if(color.length > 0 && paint.length > 0 && price.length > 0 && height.length > 0
                && structure.length == 0 && sewed.length == 0){
                var temp = [];
                color.forEach(function(oe){
                    paint.forEach(function(ie){
                        price.forEach(function(iie){
                            height.forEach(function(oie){
                                temp.push(oe +"."+ie + "."+iie.replace( /(:|\.|\[|\]|,|=|@)/g, "\\$1")+"."
                                    + oie.replace( /(:|\.|\[|\]|,|=|@)/g, "\\$1" ));
                            });
                        });
                    })
                });
                showSelectedElement(temp);
            }
            //*********************SEWED 5 > START**************************
            else if(color.length > 0 && paint.length > 0 && price.length > 0 && height.length > 0
                && structure.length == 0 && sewed.length > 0){
                var temp = [];
                color.forEach(function(oe){
                    paint.forEach(function(ie){
                        price.forEach(function(iie){
                            height.forEach(function(oie){
                                sewed.forEach(function(ooe){
                                temp.push(oe +"."+ie + "."+iie.replace( /(:|\.|\[|\]|,|=|@)/g, "\\$1")+"."
                                    + oie.replace( /(:|\.|\[|\]|,|=|@)/g, "\\$1" )+"."+ooe.replace(/\s/g, '.').toString());
                            });
                        });
                    })
                });
                });
                showSelectedElement(temp);
            }else if(color.length > 0 && paint.length > 0 && price.length > 0 && height.length == 0
                && structure.length > 0 && sewed.length > 0){
                var temp = [];
                color.forEach(function(oe){
                    paint.forEach(function(ie){
                        price.forEach(function(iie){
                            structure.forEach(function(oie){
                                sewed.forEach(function(ooe){
                                temp.push(oe +"."+ie + "."+iie.replace( /(:|\.|\[|\]|,|=|@)/g, "\\$1")+"."
                                    + oie+"."+ooe.replace(/\s/g, '.').toString());
                            });
                        });
                    });
                });
                });
                showSelectedElement(temp);
            }else if(color.length > 0 && paint.length == 0 && price.length > 0 && height.length > 0
                && structure.length > 0 && sewed.length > 0){
                var temp = [];
                color.forEach(function(oe){
                    price.forEach(function(ie){
                        height.forEach(function(iie){
                            structure.forEach(function(oie){
                                sewed.forEach(function(ooe){
                                temp.push(oe +"."+ie.replace( /(:|\.|\[|\]|,|=|@)/g, "\\$1") + "."+iie.replace( /(:|\.|\[|\]|,|=|@)/g, "\\$1")+"."
                                    + oie+"."+ooe.replace(/\s/g, '.').toString());
                            });
                        });
                    });
                });
                });
                showSelectedElement(temp);
            }else if(color.length > 0 && paint.length > 0 && price.length == 0 && height.length > 0
                && structure.length > 0 && sewed.length > 0){
                var temp = [];
                color.forEach(function(oe){
                    paint.forEach(function(ie){
                        height.forEach(function(iie){
                            structure.forEach(function(oie){
                                sewed.forEach(function(ooe){
                                temp.push(oe +"."+ie + "."+iie.replace( /(:|\.|\[|\]|,|=|@)/g, "\\$1")+"."
                                    + oie+"."+ooe.replace(/\s/g, '.').toString());
                            });
                        });
                    });
                });
                });
                showSelectedElement(temp);
            }else if(color.length == 0 && paint.length > 0 && price.length > 0 && height.length > 0
                && structure.length > 0 && sewed.length > 0){
                var temp = [];
                paint.forEach(function(oe){
                    price.forEach(function(ie){
                        height.forEach(function(iie){
                            structure.forEach(function(oie){
                                sewed.forEach(function(ooe){
                                    temp.push(oe +"."+ie.replace( /(:|\.|\[|\]|,|=|@)/g, "\\$1")+"."
                                        +iie.replace( /(:|\.|\[|\]|,|=|@)/g, "\\$1")+"."
                                        + oie+"."+ooe.replace(/\s/g, '.').toString());
                                });
                            });
                        });
                    });
                });
                showSelectedElement(temp);
            }
            else{
                var temp = [];
                color.forEach(function(oe){
                    price.forEach(function(ie){
                        paint.forEach(function(iie){
                            height.forEach(function(oie){
                                structure.forEach(function(ooe){
                                    sewed.forEach(function(oiie){
                                        temp.push(oe +"."+ie.replace( /(:|\.|\[|\]|,|=|@)/g, "\\$1" ) + "."+iie+"."+
                                             oie.replace( /(:|\.|\[|\]|,|=|@)/g, "\\$1" )+ "."+ooe+
                                             "."+oiie.replace(/\s/g, '.').toString());
                                })
                            });
                        });
                    });});
                });

                showSelectedElement(temp);
            }
        }
    });
});