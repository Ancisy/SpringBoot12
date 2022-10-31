import axiosClient from "./axiosClient";

const userApi = {
    deleteUser(id) {
        const url = `user/delete/${id}`;
        return axiosClient.delete(url);
    },

    createUser(newUser) {
        const url = "/newUser";
        return axiosClient.post(url, newUser);
    },
}

export default userApi;