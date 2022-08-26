import "./App.css";
import Header from "./components/Header/Header";
import AdminRoutes from "./routers/AdminRoutes";

function App() {
  return (
    <div className="App">
      <Header />
      <AdminRoutes />
    </div>
  );
}

export default App;
