function renderTableBooks(books) {

    const table = [];

       table.push(`<a class="btn btn-primary" href="/book" onclick="addNewBook(); return false;">Добавить книгу</a>
    <table class="table" id="book_table">
        <thead>
            <tr>
                <th scope="col">ID</th>
                <th scope="col">Название книги</th>
                <th scope="col">Жанр</th>
                <th scope="col">Изменение данных</th>
                <th scope="col">Удаление</th>
            </tr>
        </thead>
    <tbody>`);

    table.push(books.map( book => {
        return `
    <tr id="book-${book.id}">
      <th scope="row">${book.id}</th>
      <td id="bookName">${book.name}</td>
      <td id="bookGenre">${book.genre.name}</td>
      <td><button type="button" class="btn btn-primary" onclick="changeExistsBook(${book.id})">Редактировать</button></td>
      <td><button type="button" class="btn btn-danger" onclick="deleteBook(${book.id})" >Удалить</button></td>
    </tr>`}).join(''));

    table.push(`</tbody></table>`)
    return table.join('');
}



function insertContent(elementId, html) {
    document.getElementById(elementId).innerHTML = html;
}

function removeElement(elementId) {
    document.getElementById(elementId).remove();
}

async function sendRequest(url, method, data) {
    const request = {
        method,
        headers: {
            "Content-Type": "application/json"
        },
    };
    if(data) {
        request['body'] = JSON.stringify(data);
    }

    const response = await window.fetch(url, request);
    if(response.status === 200) {
        return response.json();
    } else {
        throw Error("Ошибка сервера");
    }
}


async function deleteBook(bookId) {
    try {
        const elementId = `book-${bookId}`;
        let deletedBookId = await sendRequest(window.location.origin + '/book/'+ bookId, 'DELETE', null);
        if(deletedBookId === bookId) {
            removeElement(elementId);
        }
    } catch (e) {
        console.log(e);
    }
}


async function getAllBooks(that) {
    try {
        const data = await sendRequest(that.href, 'GET', null);
        const html = renderTableBooks(data);
        insertContent('container', html);
    } catch (e) {
        console.log(e);
    }
}


async function addNewBook() {
    const MODAL_ID = 'modal';
    let genres;
    try {
        genres = await sendRequest(window.location.origin + "/genre", 'GET', null);
    } catch (e) {
        console.log(e);
    }
    const bookData = {
        id: '',
        name: '',
        genre: {
            id: 0
        }
    };
    const html = renderBookModal(genres, bookData);
    insertContent(MODAL_ID, html);
    document.getElementById(MODAL_ID).style.display = 'block';
}

function renderBookModal(genres, bookData) {
    const newBookPageElements = [];
    newBookPageElements.push(` 
            <div class="form" style="background-color: white; width: 50%; height: 50%; position: absolute; left: 25%; top: 5%; padding: 10px; border-radius: 10px;">
            <h1>Добавление новой книги</h1>
            <div onclick="closeModal();" style="position: absolute; top: 10px; right: 10px;">Закрыть</div>
            <form action="/book" method="post" name="add_new_book" onsubmit="saveNewBook(this); return false;">
                <input type="hidden" name="id_book" value="${bookData.id}" >
                <div class="form-group">
                    <label for="book_name">Название книги</label>
                    <input type="text" class="form-control" id="book_name" name="name" value="${bookData.name}">
                </div>
            <div class="form-group">
                    <label for="genre">Список жанров</label>
                    <select class="form-control" id="genre" name="genre_id">`
    );

    newBookPageElements.push(
        genres.map(genre => {
            return `<option value="${genre.id}" ${bookData.genre.id === genre.id ? "selected" : ''}>${genre.name}</option>`;
        }).join(''));
    newBookPageElements.push(`
                        </select>
                    </div>
                    <button type="submit" class="btn btn-primary">Сохранить</button>
                </form>
            </div>`
    );
    return newBookPageElements;
}


async function saveNewBook(form) {

    let method = 'POST';
    const bookName = form['book_name'].value;
    const genre = form['genre'];
     const book = {
         name: bookName,
         genre: {
             id: genre.value
         }
     };

     const id = form['id_book'].value;
     if(id) {
         book['id'] = id;
         method = 'PUT';
     }

     const response = await sendRequest(form.action, method, book);
     if(response) {
         if(id) {
             const oldRow = document.getElementById('book-' + id);
             replaceDataInRow(oldRow, response);
         } else {
             const row = createRowTable(response);
             document.getElementById('book_table').appendChild(row);
         }
         closeModal();
     }
}


function replaceDataInRow(row, book) {
    row.querySelector('#bookName').textContent = book.name;
    row.querySelector('#bookGenre').textContent = book.genre.name;
}


function createRowTable(book) {
    const trAttr = new Map();
    trAttr.set('id', 'book-' + book.id);
    const row = createNewElement('tr', trAttr, null, '');

    const thAttr = new Map();
    thAttr.set('scope', 'row');
    const th = createNewElement('th', thAttr, null, book.id);
    row.appendChild(th);

    const td = createNewElement('td', null, null, book.name);
    row.appendChild(td);

    const td1 = createNewElement('td', null, null , book.genre.name);
    row.appendChild(td1);

    const td2 = createNewElement('td', null, null, '');
    const btnEdit = document.createElement('button');
    btnEdit.setAttribute('type', 'button');
    btnEdit.classList.add('btn', 'btn-primary');
    btnEdit.setAttribute('onclick', `changeExistsBook(${book.id})`);
    btnEdit.textContent = 'Редактировать';
    td2.appendChild(btnEdit);
    row.appendChild(td2);

    const btnAttr = new Map();
    btnAttr.set('type', 'button');
    btnAttr.set('onclick', `deleteBook(${book.id})`);
    const btnClasses = ['btn', 'btn-danger'];

    const td3 = createNewElement('td', null, null, null);
    const btnDel = createNewElement('button',btnAttr, btnClasses, 'Удалить' );
    td3.appendChild(btnDel);

    row.appendChild(td3);

    return row;
}


function createNewElement(elementName, attributes, classes, text) {

    const newElement = document.createElement(elementName);

    if(attributes) {
        attributes.forEach((value, key) => {
            newElement.setAttribute(key, value);
        });
    }

    if(classes && classes.length) {
        classes.forEach(cl => {
            newElement.classList.add(cl);
        });
    }

    newElement.textContent = text;

    return newElement;

}

function closeModal() {
    const MODAL_ID = 'modal';
    const modalElement = document.getElementById(MODAL_ID);
    modalElement.innerHTML = '';
    modalElement.style.display = 'none';
}


async function changeExistsBook(bookId) {
    const MODAL_ID = 'modal';
    let genres;
    let bookData;

    try {
        bookData = await sendRequest(window.location.origin + '/book/' + bookId, 'GET', null);
        genres = await sendRequest(window.location.origin + "/genre", 'GET', null);
    } catch (e) {
        console.log(e);
    }

    const html = renderBookModal(genres, bookData);
    insertContent(MODAL_ID, html);
    document.getElementById(MODAL_ID).style.display = 'block';
}