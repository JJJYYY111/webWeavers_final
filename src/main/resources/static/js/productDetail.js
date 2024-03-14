// 리뷰 별점 개수 및 평균값
    
window.onload = function(){ // 화면이 로드가 완료되면 실행
	
    let starVal1 = 0;
    let starVal2 = 0;
    let starVal3 = 0;
    let starVal4 = 0;
    let starVal5 = 0;
    let cnt = 0;
    let starAvg = 0;
    
    var elements = document.querySelectorAll('.starValue'); // 해당 클래스를 가진 요소들을 배열로 저장
    for(e of elements){ // 반복문을 사용하여 위의 배열을 사용
		switch(parseInt(e.value)) // switch-case문을 통해서 조건에 따른 기능을 수행
		{
			case 1 : 
			starVal1 += 1;
			cnt += 1;
			break;
			
			case 2 :
			starVal2 += 1;
			cnt += 1;
			break;
			
			case 3 : 
			starVal3 += 1;
			cnt += 1;
			break;
			
			case 4 : 
			starVal4 += 1;
			cnt += 1;
			break;
			
			case 5 : 
			starVal5 += 1;
			cnt += 1;
			break;
		}
		// 콘솔로 확인해보기
		console.log("starVal1 " + starVal1 + " " + typeof(starVal1));
		console.log("starVal2 " + starVal2 + " " + typeof(starVal2));
		console.log("starVal3 " + starVal3 + " " + typeof(starVal3));
		console.log("starVal4 " + starVal4 + " " + typeof(starVal4));
		console.log("starVal5 " + starVal5 + " " + typeof(starVal5));		
		console.log(cnt + typeof(cnt));
		console.log(starAvg + typeof(starAvg));
	}
		if(cnt > 0){ // 리뷰개수가 0보다 크면 평균값을 계산하여 숫자로 출력
			let totalAvg = ((starVal1*1)+(starVal2*2)+(starVal3*3)+(starVal4*4)+(starVal5*5))*1.0/cnt
			starAvg = totalAvg.toFixed(1);
		}
		else{ // 리뷰개수가 0일경우 Nan을 방지하기위해 평균값을 0으로 초기화
			starAvg = 0;
		}
		
		// id로 해당 요소를 검색하여 해당 요소들안에 값을 입력
		document.getElementById('starVal1').innerText = starVal1;
		document.getElementById('starVal2').innerText = starVal2;
		document.getElementById('starVal3').innerText = starVal3;
		document.getElementById('starVal4').innerText = starVal4;
		document.getElementById('starVal5').innerText = starVal5;
		document.getElementById('avgScore').innerText = starAvg;		
}