
$(document).ready(function() {

    function showSelectedElement(arr){
        console.log("start function showSelectedElement"+arr);
        $('.color').hide();
        arr.forEach(function(e){
            console.log(" arr.forEach(function(e) ."+e.toString());
            $("."+e.toString()).show();
        });
    }
    var arrayPrice = [];
    var arrayMax = [];
    var maxPrice;
    var minPrice;
    var color = [];
    var paint = [];
    var structure = [];
    var price = [];

    $("div[class='searchColor'] input").change(function () {

        arrayMax = $("[class^=priceSSS]");
        for (i=0; i<arrayMax.length; i++){
            arrayPrice[i] =arrayMax[i].innerHTML;
        }
        //console.log("Price: "+arrayPrice);
        maxPrice = Math.max.apply(null, arrayPrice);
        minPrice =  Math.min.apply(null, arrayPrice);


        if($("#filterColor input:checked").length == 0 && $("#filterPrice input:checked").length == 0 &&
            $("#searchPaint input:checked").length == 0 && $("#filterHeight input:checked").length == 0
            && $("#filterStructure input:checked").length == 0
        ){
            $('.color').show();
        }else{
            var color = ["Белый","Бежевый","Кремовый","Золотой", "Желтый","Оранжевый","Персиковый","Терракотовый",
                "Кофейный", "Коричневый","Розовый","Красный", "Бордовый","Салатовый","Зеленый",
                "Голубой", "Синий", "Фиолетовый", "Серый", "Черный"];
            var paint = ["Абстрактный","Геометрический","Классический", "Однотонный","Полоска","Растительный"];
            var structure = ["Лен","Нити","Двухслойная", "Жатая","Жаккард","Сатин", "Тафта", "Двухсторонняя","Бархат",
                "Покрывальная"];
            var price = [];
            var height = ["2.7-2.9м", "2.9-3.0м", "3.0-3.15м", "3.15м"];


            if($("#filterStructure input:not(:checked)").length > 0){
                $("#filterStructure input:not(:checked)").each(function(){
                    //удаляем 1 элемент, splice служит для добавления/удаления элем из массива
                    //in this code we remove 1 elements. Splice(obj,1)
                    var k = $(this).val();
                    console.log("value unchecked: "+k);
                    structure.splice(k,1);
                })
            }
            console.log("structure: "+structure);

            if($("#filterColor input:not(:checked)").length > 0){
                $("#filterColor input:not(:checked)").each(function(){
                    //удаляем 1 элемент, splice служит для добавления/удаления элем из массива
                    //in this code we remove 1 elements. Splice(obj,1)
                    color.splice(color.indexOf($(this).attr('value')),1);
                })
            }
            console.log("color: "+color);

            if($("#filterHeight input:not(:checked)").length > 0){
                $("#filterHeight input:not(:checked)").each(function(){
                    height.splice(height.indexOf($(this).attr('value')),1);
                })
            }
            console.log("Height after SPLICE: "+height);


            if($("#searchPaint input:not(:checked)").length > 0){
                $("#searchPaint input:not(:checked)").each(function(){
                    paint.splice(paint.indexOf($(this).attr('value')),1);
                })
            }
            console.log("paint: "+paint);
            if($("#filterPrice input:checked").length > 0){
                var arr = [];
                $("#filterPrice input:checked").each(function(){
                    var s = $(this).attr('value'); //the same $(this).val();
                    for (i=0; i<arrayMax.length; i++) {
                    if(s == "1"){
                        if (arrayPrice[i] > 0 && arrayPrice[i] <= 800) {
                            //concat - copy value from selected array, now we copy in arr all info from arrayPrice[i]
                            //which to be in the selected range
                         arr = arr.concat((arrayPrice[i]));
                        }
                    }else if(s == "2") {
                        if (arrayPrice[i] > 800 && arrayPrice[i] <= 1600) {
                            arr = arr.concat(arrayPrice[i]);
                        }
                        } else {
                            if (arrayPrice[i] > 1600) {
                                arr = arr.concat(arrayPrice[i]);
                            }
                        }
                    }
                    });
                console.log("Before Arr.forEach: "+arr);
                arr.forEach(function(ele){
                    //"Привет, мир".indexOf("Привет")    // вернет 0
                    //"Привет, мир".indexOf("Корова")    // вернет -1
                    //"Привет, мир".indexOf("мир")    // вернет 8
                    if(price.indexOf(ele) == -1)
                        price.push(ele);
                });
            }
            console.log("number: "+price);

            if(color.length == 0 && paint.length == 0 && price.length > 0 && height.length == 0
                && structure.length ==0){
                $('.color').hide();
                price.forEach(function(e){

//https://learn.jquery.com/using-jquery-core/faq/how-do-i-select-an-element-by-an-id-that-has-characters-used-in-css-notation/
                    $("."+e.replace( /(:|\.|\[|\]|,|=|@)/g, "\\$1" )).show();
                });
            }else if(color.length == 0 && paint.length > 0 && price.length == 0 && height.length == 0
            && structure.length ==0){

                showSelectedElement(paint)
            //}else if(color.length == 0 && paint.length > 0 && price.length == 0 && height.length == 0
            //    && structure.length ==0){
            //
            //    showSelectedElement(paint)
            }else if(color.length == 0 && paint.length == 0 && price.length == 0 && height.length == 0
                && structure.length >0){

                showSelectedElement(structure)
            }else if(color.length == 0 && paint.length == 0 && price.length == 0 && height.length > 0
                && structure.length ==0){
                $('.color').hide();
                height.forEach(function(e){
                    console.log("if height.length>0" +e);
                    $("."+e.replace( /(:|\.|\[|\]|,|=|@)/g, "\\$1" )).show();
                });
            }else if(color.length > 0 && paint.length == 0 && price.length == 0 && height.length == 0
                && structure.length ==0){

                showSelectedElement(color)

            }else if(color.length > 0 && paint.length > 0 && price.length == 0 && height.length == 0
                && structure.length ==0){
                var temp = [];
                color.forEach(function(oe){
                    paint.forEach(function(ie){
                        temp.push(oe +"."+ie);
                    });
                });
                showSelectedElement(temp);
            }else if(color.length > 0 && paint.length == 0 && price.length == 0 && height.length == 0
                && structure.length >0){
                var temp = [];
                color.forEach(function(oe){
                    structure.forEach(function(ie){
                        temp.push(oe +"."+ie);
                    });
                });
                showSelectedElement(temp);
            }else if(color.length == 0 && paint.length > 0 && price.length == 0 && height.length == 0
                && structure.length >0){
                var temp = [];
                paint.forEach(function(oe){
                    structure.forEach(function(ie){
                        temp.push(oe +"."+ie);
                    });
                });
                showSelectedElement(temp);
            }else if(color.length == 0 && paint.length == 0 && price.length > 0 && height.length == 0
                && structure.length >0){
                var temp = [];
                price.forEach(function(oe){
                    structure.forEach(function(ie){
                        temp.push(oe.replace( /(:|\.|\[|\]|,|=|@)/g, "\\$1" ) +"."+ie);
                    });
                });
                showSelectedElement(temp);
            }else if(color.length == 0 && paint.length == 0 && price.length == 0 && height.length > 0
                && structure.length >0){
                var temp = [];
                paint.forEach(function(oe){
                    structure.forEach(function(ie){
                        temp.push(oe.replace( /(:|\.|\[|\]|,|=|@)/g, "\\$1" ) +"."+ie);
                    });
                });
                showSelectedElement(temp);
            }else if(color.length == 0 && paint.length > 0 && price.length > 0 && height.length == 0
                && structure.length ==0){
                var temp = [];
                paint.forEach(function(oe){
                    price.forEach(function(ie){
                        temp.push(oe +"."+ie.replace( /(:|\.|\[|\]|,|=|@)/g, "\\$1" ));
                    });
                });
                showSelectedElement(temp);
            }else if(color.length == 0 && paint.length > 0 && price.length == 0 && height.length > 0
                && structure.length ==0){
                var temp = [];
                paint.forEach(function(oe){
                    height.forEach(function(ie){
                        temp.push(oe +"."+ie.replace( /(:|\.|\[|\]|,|=|@)/g, "\\$1" ));
                    });
                });
                showSelectedElement(temp);
            }else if(color.length > 0 && paint.length == 0 && price.length == 0 && height.length >0
                && structure.length ==0){
                var temp = [];
                color.forEach(function(oe){
                    height.forEach(function(ie){
                        temp.push(oe +"."+ie.replace( /(:|\.|\[|\]|,|=|@)/g, "\\$1" ));
                    });
                });
                showSelectedElement(temp);
            }else if(color.length == 0 && paint.length == 0 && price.length > 0 && height.length >0
                && structure.length ==0){
                var temp = [];
                price.forEach(function(oe){
                    height.forEach(function(ie){
                        temp.push(oe.replace( /(:|\.|\[|\]|,|=|@)/g, "\\$1" ) +"."+ie.replace( /(:|\.|\[|\]|,|=|@)/g, "\\$1" ));
                    });
                });
                showSelectedElement(temp);
            }else if(color.length > 0 && paint.length == 0 && price.length > 0 && height.length == 0
                && structure.length ==0){
                var temp = [];
                color.forEach(function(oe){
                    price.forEach(function(ie){
                        temp.push(oe +"."+ie.replace( /(:|\.|\[|\]|,|=|@)/g, "\\$1" ));
                    });
                });
                showSelectedElement(temp);
            }else if(color.length > 0 && paint.length > 0 && price.length > 0 && height.length == 0
                && structure.length ==0){
                var temp = [];
                color.forEach(function(oe){
                    paint.forEach(function(ie){
                        price.forEach(function (iie) {
                            temp.push(oe + "." + ie+"." + iie.replace(/(:|\.|\[|\]|,|=|@)/g, "\\$1"));
                        });
                    });
                });
                showSelectedElement(temp);
            }else if(color.length > 0 && paint.length > 0 && price.length == 0 && height.length == 0
                && structure.length >0){
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
                && structure.length >0){
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
                && structure.length >0){
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
                && structure.length >0){
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
                && structure.length >0){
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
                && structure.length == 0){
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
                && structure.length == 0){
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
                && structure.length == 0){
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
                && structure.length == 0){
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
            }else if(color.length > 0 && paint.length == 0 && price.length > 0 && height.length > 0
                && structure.length > 0){
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
            }else if(color.length > 0 && paint.length > 0 && price.length == 0 && height.length > 0
                && structure.length > 0){
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
                && structure.length > 0){
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
                && structure.length > 0){
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
                && structure.length == 0){
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
                && structure.length == 0){
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




            else{
                var temp = [];
                color.forEach(function(oe){
                    price.forEach(function(ie){
                        paint.forEach(function(iie){
                           height.forEach(function(oie){
                               structure.forEach(function(oiie){
                                   temp.push(oe +"."+ie.replace( /(:|\.|\[|\]|,|=|@)/g, "\\$1" ) + "."+iie+"."+
                                        oie.replace( /(:|\.|\[|\]|,|=|@)/g, "\\$1" )+ "."+oiie);
                               })
                           });
                           });
                        })
                    });

                showSelectedElement(temp);
            }
        }
    });
});
