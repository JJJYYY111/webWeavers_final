/* 회원 검색 필터 */
											$("#search").on("click", function() {
												console.log('들어옴')
												var selectElement = document.getElementById("gradeName"); // select 요소 가져오기
												var gradeName = selectElement.options[selectElement.selectedIndex].text; // 선택된 option의 값 가져오기
												if(gradeName == '선택'){
                                                    gradeName = "";
                                                }
                                                //console.log(memberGrade);
												var memberName = document.getElementById("memberName").value;
                                                if(memberName == null){
                                                    memberName == "";
                                                }
												
												console.log('로그1 ['+memberName+']');
												
												var memberID= document.getElementById("memberID").value;
												//memberRegdate의 위에서 value를 가져온다.
                                                if(memberID == null){
                                                	memberID == "";
                                                }
												console.log('로그2 ['+memberID+']');
												
												$.ajax({
													
													type: "POST",
													url: "/adminMemberSearch",
													data: {
                                                        'gradeName': gradeName,
                                                        'memberName': memberName,
                                                        'memberID': memberID
                                                    },
                                                    dataType:'json',
                                                    	success:function(datas) {
                                                    		//datas = JSON.parse(datas);
                                                    		console.log('콘솔'+datas);
                                                    	    var tableHTML = "<table id='search' border='1'>";
                                                    	    tableHTML += "<thead><tr><th>ID</th><th>이름</th><th>등급</th><th>생일</th><th>가입일</th><th>email수신동의</th></tr></thead>";
                                                    	    tableHTML += "<tbody>";
                                                    	    for (var i = 0; i < datas.length; i++) {
                                                    	        tableHTML += "<tr class='memberTable' onclick=\"location.href='adminMemberStatusChange?memberID="+datas[i].memberID +"'\" id='"+datas[i].memberID+"' style='cursor: pointer;'>";
                                                    	        tableHTML += "<td>" + datas[i].memberID + "</td>";
                                                    	        tableHTML += "<td>" + datas[i].memberName + "</td>";
                                                    	        tableHTML += "<td>" + datas[i].gradeName + "</td>";
                                                    	        tableHTML += "<td>" + datas[i].memberBirth + "</td>";
                                                    	        tableHTML += "<td>" + datas[i].memberRegdate + "</td>";
                                                    	        tableHTML += "<td>" + datas[i].memberMarketing + "</td>";
                                                    	        tableHTML += "</tr>";
                                                    	    }
                                                    	    tableHTML += "</tbody></table>";
                                                    	    $("#memberTable").html(tableHTML);
                                                    	    
                                                    	    $("#memberNav").remove();
                                                    	    
                                                    	    // 검색 후 페이징 처리 함수 호출
                                                            var rowPerPage = 10;
                                                            var $memberTable = $('#memberTable');
                                                            if ($memberTable.length) {
                                                                createPagination($memberTable, 'memberNav', rowPerPage);
                                                            }
                                                    	    
                                                    },
                                                    error: function (error) {
                                                    	
                                                    	console.log('실패')
                                                        console.log('에러의 종류:' + error)
                                                    }
												});
												
											
											});
											
											
