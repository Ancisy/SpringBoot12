import UserEdit from './features/user/UserEdit';
import UserAdd from './features/user/UserAdd';
import { Route, Routes,BrowserRouter } from "react-router-dom"
function App() {
  return (
    <>
      <Routes>
        <Route path="/add" element={<UserAdd />}/>
        <Route path="/" element={<UserEdit />}/>
      </Routes>
    </>
  );
}

export default App;
