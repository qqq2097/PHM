/**
 * 
 */
/**
 * 
 */
const signUpButton = document.getElementById('signUp');
//const 함수, document 객체는 html 요소와 관련된 작업을 도와줌, 
//getElementById는 html 요소를 선택하기 위해 제공되는 메소드
const signInButton = document.getElementById('signIn');
const container = document.getElementById('container');

signUpButton.addEventListener('click', () => {
	//addEventListener 특정 이벤트 발생 시 특정 함수 실행
	container.classList.add("right-panel-active");
});

signInButton.addEventListener('click', () => {
	container.classList.remove("right-panel-active");
});