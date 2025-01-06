const url = "http://localhost:8080/api/v1/users";

//  элемент, куда будем вставлять данные ( таблица с id="user-data")
const renderTable = document.getElementById("user-data");

function fillRoles(selectId, userRoles) {
    let select = document.getElementById(selectId);
    select.innerHTML = ""; // Очищаем список перед добавлением ролей

    let allRoles = [
        {id: 1, name: 'ROLE_ADMIN'},
        {id: 2, name: 'ROLE_USER'}
    ];

    allRoles.forEach(role => {
        const option = new Option(role.name.replace('ROLE_', ''), role.id);
        option.selected = userRoles.includes(role.id); // Отмечаем роли пользователя
        select.add(option); // Добавляем роль в список
    });
}

const renderPosts = (users) => {
    let temp = '';
    users.forEach((user) => {
        let roles = user.roles.map(role => role.name.replace("ROLE_", "")).join(", ");
        temp += `
            <tr>
                <td>${user.id}</td>
                <td>${user.name}</td>
                <td>${user.surname}</td>
                <td>${user.age}</td>
                <td>${user.email}</td>
                <td>${roles}</td>
                
                <td><button class="btn btn-info btn-sm" type="button" data-toggle="modal" 
                data-target="#editModal" onclick="editModal(${user.id})">Edit</button></td>
                
                <td><button class="btn btn-danger btn-sm" type="button" data-toggle="modal"
                data-target="#deleteModal" onclick="deleteModal(${user.id})">Delete</button></td>
            </tr>
        `;
    });
    renderTable.innerHTML = temp;
}

function getAllUsers() {
    fetch(url)
        .then(res => res.json())
        .then(data => {
            renderPosts(data)
        })
        .catch(error => console.error('Ошибка загрузки данных:', error));
}

getAllUsers();


// Преобразуем выбранные роли в объект с ID
const getRoles = (rolesArray) => rolesArray.map(role => ({id: role}));

function editModal(id) {
    fetch(url + '/' + id, {
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json;charset=UTF-8'
        }
    }).then(res => res.json())
        .then(user => {
            // Заполнение данных пользователя
            document.getElementById('edit-id').value = user.id;
            document.getElementById('edit-name').value = user.name;
            document.getElementById('edit-surname').value = user.surname;
            document.getElementById('edit-age').value = user.age;
            document.getElementById('edit-email').value = user.email;

            // Поле пароля пустое (только для нового ввода)
            document.getElementById('edit-password').value = '';

            fillRoles('edit-roles', user.roles.map(role => role.id));
        })
        .catch(error => console.error('Error fetching user data:', error));
}

// Получаем форму и кнопку "Save"
const editUserForm = document.getElementById("editUserForm");
const saveButton = document.querySelector('button[type="submit"]');

// Добавляем обработчик события для нажатия клавиши в форме
editUserForm.addEventListener("keydown", async function (event) {

    // Проверяем, была ли нажата клавиша Enter (код клавиши 13)
    if (event.key === "Enter") {
        event.preventDefault();  // Предотвращаем стандартное поведение (отправка формы)
        await editUser();  // Вызываем функцию editUser()
    }
});
// Обработчик для клика по кнопке "Save"
saveButton.addEventListener("click", async function (event) {
    event.preventDefault(); // Предотвращаем отправку формы
    await editUser(); // Вызов функции редактирования
});

async function editUser() {
    const user = {
        id: document.getElementById("edit-id").value,
        name: document.getElementById("edit-name").value,
        surname: document.getElementById("edit-surname").value,
        age: document.getElementById("edit-age").value,
        email: document.getElementById("edit-email").value,
        password: document.getElementById("edit-password").value,
        roles: getRoles(Array.from(document.getElementById("edit-roles").selectedOptions)
            .map(role => role.value))
    };

    await fetch(url, {
        method: "PUT",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json;charset=UTF-8'
        },
        body: JSON.stringify(user)
    });

    getAllUsers()
    $('#editModal').modal('hide');
}

function deleteModal(id) {
    fetch(url + "/" + id, {
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json;charset=UTF-8'
        }
    }).then(res => {
        res.json().then(user => {

            document.getElementById('delete-id').value = user.id;
            document.getElementById('delete-name').value = user.name;
            document.getElementById('delete-surname').value = user.surname;
            document.getElementById('delete-age').value = user.age;
            document.getElementById('delete-email').value = user.email;

            fillRoles('delete-roles', user.roles.map(role => role.id));
        })
            .catch(error => console.error('Error fetching user data:', error));
    })

}

async function deleteUser() {
    await fetch(url + "/" + document.getElementById('delete-id').value, {
        method: 'DELETE',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json;charset=UTF-8'
        },
    })
    await getAllUsers()
    $('#deleteModal').modal('hide');
}


// Слушаем событие открытия модального окна для добавления нового пользователя
$('#newUserModal').on('show.bs.modal', function () {
    // Очистка всех полей перед открытием
    document.getElementById('new-firstName').value = '';
    document.getElementById('new-lastName').value = '';
    document.getElementById('new-age').value = '';
    document.getElementById('new-email').value = '';
    document.getElementById('new-password').value = '';


    // Очистка выбранных ролей
    const rolesSelect = document.getElementById('new-roles');
    for (let option of rolesSelect.options) {
        option.selected = false;  // Снимаем выделение с ролей
    }
});


const newUserForm = document.getElementById("newUserForm");

newUserForm.addEventListener('submit', async function (e) {
    e.preventDefault(); // Остановка стандартной отправки


    // Собираем данные
    let newUser = {
        name: document.getElementById("new-firstName").value,
        surname: document.getElementById("new-lastName").value,
        age: document.getElementById("new-age").value,
        email: document.getElementById("new-email").value,
        password: document.getElementById("new-password").value,
        /**/
        roles: Array.from(document.getElementById("new-roles").selectedOptions).map(option => `ROLE_${option.value}`)
    };

    // Отправляем данные на сервер
    const response = await fetch(url, {
        method: "POST",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json;charset=UTF-8'
        },
        body: JSON.stringify(newUser)
    });

    // Обновляем таблицу и закрываем модалку
    getAllUsers();
    $('#newUserModal').modal('hide');
});

const adminData = document.getElementById("data-admin");
const urlAuth = "http://localhost:8080/api/v1/users/auth";
const panel = document.getElementById("user-panel");

function userAuthInfo() {
    fetch(urlAuth)
        .then((res) => res.json())
        .then((user) => {
            let temp = '';
            temp += `<tr>
            <td>${user.id}</td>
            <td>${user.name}</td>
            <td>${user.surname}</td>
            <td>${user.age}</td>
            <td>${user.email}</td>
            <td>${user.role}</td>
            </tr>`;
            adminData.innerHTML = temp;
        });
}

userAuthInfo()
// Если на странице "/user", показываем информацию о пользователе
if (window.location.pathname === '/user') {
    userAuthInfo();  // Загружаем информацию о текущем пользователе на страницу пользователя
}

document.getElementById('userLink').addEventListener('click', (e) => {
    userAuthInfo();  // Загружаем данные о пользователе при переходе на страницу User
});


function userPanel() {
    fetch(urlAuth)
        .then((res) => res.json())
        .then((user) => {
            // Получаем роли пользователя, если они есть
            let roles = user.roles && user.roles.length > 0
                ? user.roles.map(role => role.name.replace("ROLE_", "")).join(", ")
                : "No roles";  // Если нет ролей, отображаем "No roles"


            // Обновляем панель с информацией о пользователе
            panel.innerHTML = `<h6>${user.email} with roles: ${roles}</h6>`;
        })
        .catch((error) => {
            console.error("Ошибка при загрузке данных о пользователе:", error);
            panel.innerHTML = `<h5>Error loading user data</h5>`; // В случае ошибки выводим сообщение
        });
}

userPanel()



