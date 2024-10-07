import pygame
import random
import time
import math

pygame.init()

width, height = 1000, 600
window = pygame.display.set_mode((width, height))
pygame.display.set_caption("Merin's Game")

window.fill((120, 200, 80))
owidth, oheight = 50, 50

def draw(obj, color):
    pygame.draw.rect(window, color, obj)

def draw_arrow(surface, color, center, direction):
    arrow_size = 20
    thickness = 4
    arrow_surface = pygame.Surface((arrow_size * 2, arrow_size * 2), pygame.SRCALPHA)
    arrow_surface.set_alpha(90)  # Set transparency (0 = fully transparent, 255 = fully opaque)

    if direction == "left":
        points = [
            (arrow_size - arrow_size//2, arrow_size),
            (arrow_size + arrow_size//2, arrow_size - arrow_size//2),
            (arrow_size + arrow_size//2, arrow_size - arrow_size//2 + thickness),
            (arrow_size - arrow_size//2 + thickness, arrow_size),
            (arrow_size + arrow_size//2, arrow_size + arrow_size//2 - thickness),
            (arrow_size + arrow_size//2, arrow_size + arrow_size//2),
        ]
    elif direction == "right":
        points = [
            (arrow_size + arrow_size//2, arrow_size),
            (arrow_size - arrow_size//2, arrow_size - arrow_size//2),
            (arrow_size - arrow_size//2, arrow_size - arrow_size//2 + thickness),
            (arrow_size + arrow_size//2 - thickness, arrow_size),
            (arrow_size - arrow_size//2, arrow_size + arrow_size//2 - thickness),
            (arrow_size - arrow_size//2, arrow_size + arrow_size//2),
        ]
    elif direction == "up":
        points = [
            (arrow_size, arrow_size - arrow_size//2),
            (arrow_size - arrow_size//2, arrow_size + arrow_size//2),
            (arrow_size - arrow_size//2 + thickness, arrow_size + arrow_size//2),
            (arrow_size, arrow_size - arrow_size//2 + thickness),
            (arrow_size + arrow_size//2 - thickness, arrow_size + arrow_size//2),
            (arrow_size + arrow_size//2, arrow_size + arrow_size//2),
        ]
    else:  # down
        points = [
            (arrow_size, arrow_size + arrow_size//2),
            (arrow_size - arrow_size//2, arrow_size - arrow_size//2),
            (arrow_size - arrow_size//2 + thickness, arrow_size - arrow_size//2),
            (arrow_size, arrow_size + arrow_size//2 - thickness),
            (arrow_size + arrow_size//2 - thickness, arrow_size - arrow_size//2),
            (arrow_size + arrow_size//2, arrow_size - arrow_size//2),
        ]

    pygame.draw.polygon(arrow_surface, color, points)
    surface.blit(arrow_surface, (center[0] - arrow_size, center[1] - arrow_size))


class Bullet:
    def __init__(self, x, y, direction):
        self.original_surface = pygame.Surface((30, 20), pygame.SRCALPHA)
        self.original_surface.fill((255, 0, 0))

        if direction is not None:
            self.direction = direction
        else:
            self.direction = pygame.K_UP

        self.rotated_surface = pygame.transform.rotate(self.original_surface, 90) if self.direction in [pygame.K_UP, pygame.K_DOWN] else self.original_surface
        self.rect = self.rotated_surface.get_rect(x=x, y=y)

    def move(self):
        movement = {"left": (-10, 0), "right": (10, 0), "up": (0, -10), "down": (0, 10), "z": (0, 0)}

        key_name = pygame.key.name(self.direction)
        self.rect.x += movement.get(key_name, (0, 0))[0]
        self.rect.y += movement.get(key_name, (0, 0))[1]

class Enemy:
    def __init__(self, x, y, spawn_from_edge=False):
        self.hit_count = 0
        self.original_surface = pygame.Surface((owidth, oheight), pygame.SRCALPHA)
        pygame.draw.rect(self.original_surface, (220, 20, 60), (0, 0, owidth, oheight))
        self.rect = self.original_surface.get_rect(x=x, y=y)

        if spawn_from_edge:
            self.rect.x, self.rect.y = (0, random.randint(0, height - oheight)) if random.choice(
                [True, False]) else (width - owidth, random.randint(0, height - oheight))
        else:
            self.rect.x, self.rect.y = x, y

        self.speed = 0.6
        self.freeze_timer = 0.0

    def duplicate(self):
        return Enemy(self.rect.x, self.rect.y)

    def move_towards_obj1(self, obj1, clock):
        if self.freeze_timer <= 0:
            self.rect.x += self.speed if self.rect.x < obj1.x else -self.speed
            self.rect.y += self.speed if self.rect.y < obj1.y else -self.speed
        else:
            self.freeze_timer = max(0, self.freeze_timer - clock.get_time() / 1000.0)

def main():
    obj1 = pygame.Rect(500, 300, owidth, oheight)
    enemies, bullets = [], []

    bullet_speed, bullet_direction, last_key_pressed = 10, "", pygame.K_RIGHT
    bullet_visible, bullet_cooldown, last_bullet_time = False, 0.25, 0
    enemy_spawn_cooldown, last_enemy_spawn_time = 2, time.time()
    max_enemies = 2

    clock = pygame.time.Clock()
    run = True
    bullet_hit_count = {}

    # Black color for the arrow
    ARROW_COLOR = (0, 0, 0)

    while run:
        window.fill((120, 200, 80))
        obj1_rect = pygame.Rect(obj1.x, obj1.y, owidth, oheight)
        for event in pygame.event.get():
            if event.type == pygame.QUIT:
                run = False
                break

        keys = pygame.key.get_pressed()
        current_time = time.time()

        horizontal_movement = 0
        vertical_movement = 0

        for dir in ["left", "right", "up", "down"]:
            if keys[getattr(pygame, f"K_{dir.upper()}")]:
                last_key_pressed, bullet_direction = getattr(pygame, f"K_{dir.upper()}"), dir
                horizontal_movement += -2 if dir == "left" else 2 if dir == "right" else 0
                vertical_movement += -2 if dir == "up" else 2 if dir == "down" else 0

        obj1.x += horizontal_movement
        obj1.y += vertical_movement

        if keys[pygame.K_z] and current_time - last_bullet_time > bullet_cooldown:
            bullet_rect = obj1.move(owidth // 2 - 10, oheight // 2 - 10)

            if isinstance(last_key_pressed, tuple):
                new_bullet = Bullet(*bullet_rect.topleft, last_key_pressed)
            else:
                bullet_x, bullet_y = obj1.x - 2 if last_key_pressed == pygame.K_LEFT else obj1.x + owidth - 28, obj1.y + oheight // 2 - 10
                new_bullet = Bullet(bullet_x, bullet_y, last_key_pressed)

                bullets.append(new_bullet)

            bullet_visible = True
            last_bullet_time = current_time

        bullets_to_remove = []
        for bullet in bullets:
            window.blit(bullet.rotated_surface, bullet.rect)
            bullet.move()

        enemies_to_remove = []
        for bullet in bullets:
            for enemy in enemies:
                if bullet.rect.colliderect(enemy.rect):
                    bullets_to_remove.append(bullet)

                    if enemy not in bullet_hit_count:
                        bullet_hit_count[enemy] = 1
                    else:
                        bullet_hit_count[enemy] += 1
                        if bullet_hit_count[enemy] >= 2:
                            enemies_to_remove.append(enemy)

        enemies[:] = [enemy for enemy in enemies if enemy not in enemies_to_remove]
        bullets = [b for b in bullets if b not in bullets_to_remove]
        bullet_visible = False

        for enemy in enemies:
            enemy.move_towards_obj1(obj1, clock)

        if current_time - last_enemy_spawn_time > enemy_spawn_cooldown and len(enemies) < max_enemies:
            new_enemy = Enemy(random.randint(0, width - owidth), random.randint(0, height - oheight), spawn_from_edge=True).duplicate()
            enemies.append(new_enemy)
            last_enemy_spawn_time = current_time

        draw(obj1_rect, (83, 104, 120, 200))

        # Draw the chevron-style direction arrow
        arrow_direction = pygame.key.name(last_key_pressed)
        draw_arrow(window, ARROW_COLOR, obj1_rect.center, arrow_direction)

        for enemy in enemies:
            draw(enemy.rect, (220, 20, 60))

        pygame.display.update()
        clock.tick(60)

    pygame.quit()

if __name__ == "__main__":
    main()
