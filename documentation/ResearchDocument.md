# Research Document

# Front end: React vs Vue vs Angular

## Step 1: Introduction:

The JavaScript world is a rich environment with hundreds of tools, libraries, and frameworks and the number continues to grow each and every day. The focus on this will be the three most used frameworks/libraries for developing UI:

- **React**
- **Vue**
- **Angular**

## Step 2: Create the main question

**Which Front End framework/library is the best?**

## Step 3: Create sub-questions

- Which framework/library is more popular?
- Which framework/library is easier to learn?
- Which framework/library has more learning resources available?
- Advantages and disadvantages of each framework/library.
- Which framework/library will be better for your application?

## Step 4: Choose methods

### **Which library is more popular?**

A quick comparison between the three frameworks/libraries shows that for the past 6 months the React package is downloaded roughly 6 times more than Angular and Vue.

This graph from NPM Trends shows that React is downloaded roughly six times more than Vue and Angular in the past six months

### **Which framework/library is easier to learn?**

According to developers and experts, React and Vue are easier to learn compared to Angular. I also think that React and Vue are easier to learn than Angular because Angular requires the use of TypeScript which is not mandatory in React and Vue.

### **Which framework/library has more learning resources available?**

Besides the framework documentation, we still need some additional courses, books, tutorials, and articles. Not any additional resources but ones from experienced authors.

There is an abundance of resources for React and Angular. Almost every Front-end oriented educational website will have articles about them, maybe even a full video course or a book.

Vue.js kind of hits the middle ground: It has good documentation and there are a few nice courses you can choose from.

### **Advantages and disadvantages of each framework/library.**

React is a UI library, Angular is a fully-fledged front-end framework, while Vue.js is a progressive framework. They can be used almost interchangeably, so it makes sense to compare them and understand their differences. They are used for creating [Single Page Applications](https://developer.mozilla.org/en-US/docs/Glossary/SPA)

### **React**

React doesn`t enforce a specific project structure. You can start using React with just a few lines of code - [Hello World – React example](https://reactjs.org/docs/hello-world.html)

```
1  ReactDOM.render(
2   <h1>Hello, world!</h1>,
3   document.getElementById('root')
4   );
```

React uses the [JSX](https://reactjs.org/docs/introducing-jsx.html) (JavaScript and XML) syntax, a syntax extension that allows you to create elements that contain HTML and JavaScript at the same time.

### **Advantages**

- Easy to learn
- Uses Virtual DOM that achieves optimal efficiency by re-rendering nodes as desired.
- Server-Side Rendering
- Easy to maintain larger apps thanks to components
- Relatively better SEO in comparison with Angular and Vue

### **Disadvantages**

- Comes only with the view layer of the application
- Complex configuration for integrating React in MVC (need to import libraries for state and model as React doesn`t implement MVC)

---

### **Vue**

The Vue.js core library focuses only on the View layer. It is called a progressive framework.

Core features:

- Declarative Rendering - Vue extends standard HTML with a template syntax that allows us to declaratively describe HTML output based on JavaScript State.
- Reactivity - Vue automatically tracks JavaScript state changes and efficiently updates the DOM when changes happen.

[Minimal Example](https://vuejs.org/guide/introduction.html#what-is-vue) - the example below demonstrates the core Vue features

```
1  import { createApp } from 'vue'
2
3  createApp({
4    data() {
5      return {
6       count: 0
7      }
8    }
9  }).mount('#app')
```

```
1   <div id="app">
2       <button @click="count++">
3           Count is: {{ count }}
4       </button>
5   </div>

```

### **Advantages**

- Lightweight
- User-friendly
- Beginner-friendly
- Reusable
- Virtual DOM

### **Disadvantages**

- Excessive code flexibility.
- Difficulties with mobile support.
- Difficulties with two-way binding.
- Lack of highly experienced experts.

### **Angular**

### **Advantages**

- Allows data binding to HTML
- Allows maximum functionalities with less coding
- Uses dependency injection while allowing separation of concerns
- MVVM (Model-View-View-Model) - enables developers to operate with the same collection of data separately on the same application.
- Structure and architecture build especially for better project scalability.

### **Disadvantages**

- Confusing and harder to learn
- Third-party integration is very difficult
- Constantly updating (this can create problems for developers when it comes to adapting to changes)
- Doesn`t provide good scope for server-side authentication

### **Which framework/library will be better for your application?**

### **Angular is better if:**

- You are building enterprise-grade and large applications

### **React is better if:**

- You require better SEO

### **Vue is better if:**

- You are building a lightweight application
- Speed is critical to your application
- There is a requirement to integrate into an existing application

### **Conclusion**

React, Vue and Angular are the most popular frameworks and obviously are most often chosen for a project. In the end. the choice depends on the type of SPA you are building and its features.

I chose React because it is the most popular and easiest to learn nowadays. With more material to study I feel more confident stepping into an unknown library.

## Used Literature:

1. NPM Trends comparison between the 3 frameworks - [@angular/core vs react vs vue | npm trends](https://www.npmtrends.com/@angular/core-vs-react-vs-vue)
2. Single Page Application - [SPA (Single-page application) - MDN Web Docs Glossary: Definitions of Web-related terms | MDN](https://developer.mozilla.org/en-US/docs/Glossary/SPA)
3. React Hello World example - [Hello World – React](https://reactjs.org/docs/hello-world.html)
4. Introduction to Vue - [Introduction | Vue.js](https://vuejs.org/guide/introduction.html#what-is-vue)
5. Marin Kaluza and Bernad Vukelic “Comparisions of front end frameworks for web applications”, vol 6 (2018), [https://www.researchgate.net/publication/328958566_Comparison_of_front-end_frameworks_for_web_applications_development](https://www.researchgate.net/publication/328958566_Comparison_of_front-end_frameworks_for_web_applications_development)
6. What to choose(2020) – React, Angular or Vue.js - [React vs Angular vs Vue.js — What to choose in 2019? (updated)](https://medium.com/techmagic/reactjs-vs-angular5-vs-vue-js-what-to-choose-in-2018-b91e028fa91d)
