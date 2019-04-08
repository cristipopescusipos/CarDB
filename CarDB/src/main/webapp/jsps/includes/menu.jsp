
<div>
    <div class="container p-3 mb-2 bg-light text-dark">
    <nav class="navbar navbar-light navbar-expand-md navigation-clean p-3 mb-2 bg-light text-dark ">
        <div class="container"><a class="navbar-brand text-break text-primary" href="http://localhost:8081/index" data-bs-hover-animate="flash"><i class="fas fa-car"></i>&nbsp;CarDB</a><button class="navbar-toggler" data-toggle="collapse" data-target="#navcol-1"><span class="sr-only">Toggle navigation</span><span class="navbar-toggler-icon"></span></button>
            <div
                    class="collapse navbar-collapse" id="navcol-1">
                <ul class="nav navbar-nav ml-auto">
                    <li class="nav-item" role="presentation"><a class="nav-link text-primary border rounded" href="http://localhost:8081/owner" data-bs-hover-animate="flash"><i class="fa fa-user"></i>&nbsp;Car Owners List</a></li>
                    <li class="nav-item" role="presentation"><a class="nav-link text-primary border rounded" href="http://localhost:8081/car" data-bs-hover-animate="flash"><i class="fa fa-list-alt"></i>&nbsp;Cars List</a></li>
                    <li class="nav-item" role="presentation"><a class="nav-link text-danger border rounded" href="http://localhost:8081/alert" data-bs-hover-animate="flash"><i class="fa fa-calendar"></i>&nbsp;Alerts</a></li>
                    <li class="dropdown nav-item">
                        <a class="dropdown-toggle nav-link text-danger border rounded" data-toggle="dropdown" aria-expanded="false" href="http://localhost:8081/alert" data-bs-hover-animate="flash">
                            <span class="badge">${requestScope.totalCount != 0 ? requestScope.totalCount : '' }</span><i class="fa fa-gears"></i>&nbsp;
                            Alerts By Items
                        </a>
                        <div class="dropdown-menu" role="menu">
                            <a class="dropdown-item text-danger border rounded" role="presentation" href="http://localhost:8081/alert?type=rca" data-bs-hover-animate="flash">
                                <span style="color:red" class="badge">${requestScope.rcaCount != 0 ? requestScope.rcaCount : '' }</span>
                                Alert by Rca
                            </a>
                            <a class="dropdown-item text-danger border rounded" role="presentation" href="http://localhost:8081/alert?type=itp" data-bs-hover-animate="flash">
                                <span style="color:red" class="badge">${requestScope.itpCount != 0 ? requestScope.itpCount : '' }</span>
                                Alert by Itp
                            </a>
                            <a class="dropdown-item text-danger border rounded" role="presentation" href="http://localhost:8081/alert?type=rovinieta" data-bs-hover-animate="flash">
                                <span style="color:red" class="badge">${requestScope.rovinietaCount != 0 ? requestScope.rovinietaCount : '' }</span>
                                Alerta Rovinieta
                            </a>
                            <a class="dropdown-item text-danger border rounded" role="presentation" href="http://localhost:8081/alert?type=fireExt" data-bs-hover-animate="flash">
                                <span style="color:red" class="badge">${requestScope.fireExtCount != 0 ? requestScope.fireExtCount : '' }</span>
                                Alerta Fire Ext.
                            </a>
                            <a class="dropdown-item text-danger border rounded" role="presentation" href="http://localhost:8081/alert?type=firstAid" data-bs-hover-animate="flash">
                                <span style="color:red" class="badge">${requestScope.firstAidCount != 0 ? requestScope.firstAidCount : '' }</span>
                                Alerta First Aid
                            </a>
                        </div>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
</div>
</div>


