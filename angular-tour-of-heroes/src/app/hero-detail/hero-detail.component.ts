import { Component, Input } from '@angular/core';
import { Location, NgIf, NgFor, UpperCasePipe } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { Hero } from '../hero';
import { Superpower } from '../superpower';
import { HeroService } from '../hero.service';

@Component({
  standalone: true,
  selector: 'app-hero-detail',
  templateUrl: './hero-detail.component.html',
  styleUrls: ['./hero-detail.component.css'],
  imports: [FormsModule, NgIf, NgFor, UpperCasePipe],
})
export class HeroDetailComponent {
  @Input() hero?: Hero;
  newSuperPower: string = '';

  constructor(
    private readonly route: ActivatedRoute,
    private readonly heroService: HeroService,
    private readonly location: Location
  ) { }

  ngOnInit(): void {
    this.getHero();
  }

  getHero(): void {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    this.heroService.getHero(id)
      .subscribe(hero => this.hero = hero);
  }

  updateHero(): void {
    if (this.hero) {
      this.heroService.updateHero(this.hero).subscribe();
    }
  }

  goBack(): void {
    this.location.back();
  }

  addSuperPower(): void {
    if (this.newSuperPower.trim() && this.hero) {
      this.hero.superpowers.push({
        name: this.newSuperPower.trim(),
        heroId: this.hero.id
      });
      this.newSuperPower = '';
      this.updateHero();
    }
  }

  removeSuperPower(index: number): void {
    this.hero?.superpowers.splice(index, 1);
    this.updateHero();
  }
}