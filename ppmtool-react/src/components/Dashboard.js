import React, { Component } from "react";
import ProjectItem from "./Project/ProjectItem";
import CreateProjectButton from "./Project/CreateProjectButton";
import { Link } from "react-router-dom";
import { connect } from "react-redux";
import { getProjects } from "../actions/projectAction";

import PropTypes from "prop-types";

class Dashboard extends Component {
  componentDidMount() {
    this.props.getProjects();
  }
  render() {
    const { projects } = this.props.project;
    return (
      <div className="projects">
        <div className="container">
          <div className="row">
            <div className="col-md-12">
              <h1 className="display-4 text-center">Projects</h1>
              <br />
              <Link to="ProjectForm.html" className="btn btn-lg btn-info">
                <CreateProjectButton></CreateProjectButton>
              </Link>
              <br />
              <hr />
              {projects.map((project) => (
                <ProjectItem key={project.id} project={project} />
              ))}
            </div>
          </div>
        </div>
      </div>
    );
  }
}

Dashboard.protoTypes = {
  getProjects: PropTypes.func.isRequired,
  project: PropTypes.object.isRequired,
};

const mapStateToProp = (state) => ({
  //state.project points towards my reducer
  project: state.project,
});

export default connect(mapStateToProp, { getProjects })(Dashboard);
