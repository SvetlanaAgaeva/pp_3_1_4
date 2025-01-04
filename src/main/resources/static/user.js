const data = document.getElementById("user-data");
const url = "http://localhost:8080/api/v1/users/auth";
const panel = document.getElementById("user-panel");

function userAuthInfo() {
    console.log("Запрос к URL:", url);  // Логируем URL для отладки
    fetch(url)
        .then((res) => res.json()) // Преобразуем ответ в JSON
        .then((user) => {
            console.log("Полученные данные:", user);  // Логируем полученные данные

            let temp = '';
            let roles = user.roles ? user.roles.map(role => role.name.replace("ROLE_", "")).join(", ") : "No roles";
            temp += `<tr>
            <td>${user.id}</td>
            <td>${user.name}</td>
            <td>${user.surname}</td>
            <td>${user.age}</td>
            <td>${user.email}</td>
            <td>${roles}</td>
            </tr>`;
            data.innerHTML = temp;


        });
}

userAuthInfo()

function userPanel() {
    fetch(url)
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
