/**
 * 유저리스트화면 
 */

$(function() {
	// userList id를 가진 테이블의 한 행을 클릭했을 때 함수가 실행
	$('#userList tbody tr').on('click', function() {
		console.log("클릭")
		// 모든 행(tr)의 table-active 클래스를 삭제하고 
		$('#userList tbody tr').removeClass('table-active');
		// 클릭한 행(this)에 table-activ 클래스를 add해준다.
		$(this).addClass('table-active');
		// updateBtn id를 가진 태그의 disabled attribute를 삭제해주면
		// 해당 버튼이 활성화 상태가 된다.
		$('#updateBtn').removeAttr('disabled');
		// 그리고 deleteDummnyBtn id를 가진 태그의 disabled attribute를 삭제해주면
		// 해당 버튼이 활성화 상태가 된다.
		$('#deleteDummyBtn').removeAttr('disabled');

		
		console.log(this)
		editSelectedUserId($(this));
	})

	$('#deleteOkBtn').click(function() {
		$('#deleteBtn').trigger('click');
	})


})

/**
 * 테이블에서 선택한 userId를 hidden저장소에 보관
 * 
 */
function editSelectedUserId(row) {
	// id값을 가진 td들을 each문으로 돌리면서 
	row.find('td').each(function() {
		let columnId = $(this).attr('id');
		console.log(columnId)
		if(columnId.startsWith('userId_')) {
			console.log($(this).text())
			$('#selectedUserId').val($(this).text());
			return false;
		}
	})
	//const columnId = document.
	
}
