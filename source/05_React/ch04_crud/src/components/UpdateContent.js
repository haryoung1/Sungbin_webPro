import { Component } from "react";

class UpdateContent extends Component {
    constructor(props) {
        super(props);
        this.state = {
            id : this.props.data.id,
            title : this.props.data.title,
            desc : this.props.data.desc,
        };
    }
inputFormHander(event) {
    this.setState({
        [event.target.name] : event.target.value,
    });
}
    render() {
        return (
            <article>
                <h2>UPDATE</h2>
                <form action="/create_process" method="get" onSubmit={function(event){
                    event.preventDefault();
                    // console.log(event.target.desc.value);
                    // debugger;
                    this.props.onChangePage(
                        this.state.id,
                        this.state.title, // event.target.title.value
                        this.state.desc,  // event.target.desc.value
                        );
                }.bind(this)}>
                    <p>
                        <input type="text" name="title" placeholder="title" 
                                value={this.state.title}
                                onChange={this.inputFormHander.bind(this)}
                    />
                    </p>
                    <p>
                        <textarea name="desc" placeholder="desc" 
                                value={this.state.desc}
                                onChange={this.inputFormHander.bind(this)}
                    />
                    </p>
                    <p><input type="submit" value="수정"></input></p>
                </form>
            </article>
        );
    }
}

export default UpdateContent;