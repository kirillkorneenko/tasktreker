import React from "react";
import ReactDOM from "react-dom";
import ManagerIndex from "./components/admin/ManagerIndex.jsx";
import SearchUsers from "./components/admin/SearchUsers.jsx";
import HomeAdmin from "./components/admin/HomeAdmin.jsx";
import DrawTasksAdmin from "./components/admin/DrawTasksAdmin.jsx";
import DrawTasksUser from "./components/user/DrawTasksUser.jsx";
import DrawProjectsAdmin from "./components/admin/DrawProjectsAdmin.jsx";
import DrawProjectsUser from "./components/user/DrawProjectsUser.jsx";
import {BrowserRouter, Route, Switch} from "react-router-dom";
import HomeUser from "./components/user/HomeUser.jsx";
import DeveloperIndex from "./components/user/DeveloperIndex.jsx";
import DrawComments from "./components/user/DrawComments.jsx";
import jquery from "jquery";
import TaskFilter from "./components/user/TaskFilter.jsx";
import LoginIndex from "./components/login/LoginIndex.jsx";
import Login from "./components/login/autorization.jsx";
import Registration from "./components/login/registration.jsx";

class App extends React.Component {
    constructor(props) {
        super(props);
        this.getAuth = this.getAuth.bind(this)
        this.state = {auth: ''};
    }

    componentDidMount() {
        this.getAuth();
    }

    getAuth() {
        var self = this
        jquery.ajax({url: "http://localhost:8080/authorization"}).then(function (data) {
            self.setState({auth: data.status})

        })
    }

    render() {
        switch (this.state.auth) {
            case "ROLE_MANAGER":
                return (<BrowserRouter >
                    <div>
                        <Route path='/' component={ManagerIndex}/>
                        <Switch>


                        </Switch>
                    </div>
                </BrowserRouter>);
                break;
            case "ROLE_DEVELOPER":
                return (<BrowserRouter >
                    <div>
                        <Route path='/' component={DeveloperIndex}/>
                        <Switch>


                        </Switch>
                    </div>
                </BrowserRouter>);
                break;
            default :
                return (<BrowserRouter >
                    <div>
                        <Route path='/' component={LoginIndex}/>
                        <Switch>
                            <Route exact path='/' component={Login}/>
                            <Route path='/registration' component={Registration}/>
                        </Switch>
                    </div>
                </BrowserRouter>);
                break;
        }
    };
}


ReactDOM.render(<App/>, document.getElementById('root'))
