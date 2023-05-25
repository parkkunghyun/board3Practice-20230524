const deleteBtn = document.getElementById('delete-btn');

if(deleteBtn) {
    deleteBtn.addEventListener( 'click', event => {
        let id = document.getElementById('article-id').value;
        fetch(`/api/articles/${id}`, {
            method: 'DELETE'
        }).then(() => {
            alert('삭제 되었습니다')
            location.replace('/articles');
        })
    });
}

const modifyBtn = document.getElementById('modify-btn');
if(modifyBtn) {
    modifyBtn.addEventListener('click', event => {
        let params =new URLSearchParams(location.search);
        let id = params.get('id');

        fetch(`/api/articles/${id}`, {
            method: 'PUT',
            headers: {
                "Content-Type": "application/json",
            }, body: JSON.stringify({
                title: document.getElementById('title').value,
                content: document.getElementById('content').value
            })
        }).then(() => {
            alert('수정이 완료 되었습니다')
            location.replace(`/articles/${id}`)
        })
    })
}

const createBtn = document.getElementById('create-btn');
if(createBtn) {
    createBtn.addEventListener('click', (event) => {
        fetch('/api/articles', {
            method: 'POST',
            headers: {
                "Content-Type": "application/json"
            }, body: JSON.stringify({
                title: document.getElementById('title').value,
                content: document.getElementById('content').value
            })
        }).then(() => {
            alert("등록 완료")
            location.replace("/articles")
        })
    })
}
