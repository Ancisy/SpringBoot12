import React from 'react';
import ReactDOM from 'react-dom/client';
import App from './App';
import UserList from './features/user/UserList';
import UserEdit from './features/user/UserEdit';
import "bootstrap/dist/css/bootstrap.min.css";
import "bootstrap/dist/js/bootstrap.bundle";
import UserAdd from './features/user/UserAdd';


const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <React.StrictMode>
    <App />
    <UserAdd/>
  </React.StrictMode>
);
