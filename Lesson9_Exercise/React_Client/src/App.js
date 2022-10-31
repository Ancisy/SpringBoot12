import UserEdit from './features/user/UserEdit';
import UserAdd from './features/user/UserAdd';
import UserList from './features/user/UserList';
import { Route, Routes} from "react-router-dom"
import Header from './features/components/header.jsx';

function App() {
  return (
    <>
    <Header/>
    <Routes >
        <Route path="/add" element={<UserAdd />}/>
        <Route path="/edit" element={<UserEdit />}/>
        <Route path="/list" element={<UserList/>}/>
      </Routes>

    {/* <Footer/> */}
    </>
  );
}

export default App;
