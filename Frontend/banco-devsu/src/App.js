
import './App.css';
import Header from './components/Header/Header';
import Nav from './components/Nav/Nav';
import FlexBox from './components/FlexBox/FelxBox';
import AdminRoutes from './routers/AdminRoutes';

function App() {
  return (
    <div className="App">
      <Header/>
      <AdminRoutes/>
    </div>
  );
}

export default App;