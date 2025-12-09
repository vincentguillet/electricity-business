import { Component, signal } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import {MainNavbarComponent} from './components/main-navbar/main-navbar.component';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, MainNavbarComponent],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {
  protected readonly title = signal('client');
}
