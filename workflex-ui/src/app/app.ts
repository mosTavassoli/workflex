import {Component, OnInit, signal} from '@angular/core';
import {RouterOutlet} from '@angular/router';
import {CommonModule} from '@angular/common';
import {HelloService} from './services/hello';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, CommonModule],
  templateUrl: './app.html',
  styleUrl: './app.scss'
})

export class App implements OnInit {
  message = '';

  constructor(private helloService: HelloService) {
  }

  ngOnInit(): void {
    this.helloService.getMessage().subscribe({
      next: (data) => this.message = data,
      error: (err) => console.error('Error fetching backend message:', err)
    });
  }
}
